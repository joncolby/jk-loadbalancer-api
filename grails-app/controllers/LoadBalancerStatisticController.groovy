

class LoadBalancerStatisticController {

    boolean transactional = false
    def JkService jkService

    def index = {}

    def stats = {
        def lbname = params?.lbname
        def netmask = params?.netmask ?: null

        def loadBalancerList = []

        if (lbname) {
              loadBalancerList << lbname.toString().toLowerCase()
        } else {
               loadBalancerList = ModJkLoadbalancer.list().collect { it.name }.unique()
        }

        def statsMap = [:]

        loadBalancerList.each { lb ->
         def active = jkService.searchLoadBalancer(lb,"ACT","", netmask)
         def activeError = jkService.searchLoadBalancer(lb,"ACT","ERR",netmask)
         def activeOK = jkService.searchLoadBalancer(lb,"ACT","OK", netmask)
         def stopped = jkService.searchLoadBalancer(lb,"STP","", netmask)
         def disabled = jkService.searchLoadBalancer(lb,"DIS","", netmask)
         def total = jkService.searchLoadBalancer(lb,"","", netmask)

         if (active)
         statsMap["${lb}.ACTIVE"] = active

         if (activeError)
         statsMap["${lb}.ACTIVE.ERROR"] = activeError

            if (active && activeError) {
                def percentActiveError = activeError / active * 100
                statsMap["${lb}.ACTIVE.ERROR.PERCENT"] = percentActiveError.stripTrailingZeros().toPlainString()
            }

         if (activeOK)
         statsMap["${lb}.ACTIVE.OK"] = activeOK

            if ( active && activeOK) {
                def percentActiveOk = activeOK / active * 100
                statsMap["${lb}.ACTIVE.OK.PERCENT"] = percentActiveOk.stripTrailingZeros().toPlainString()
            }


         if (stopped)
            statsMap["${lb}.STOPPED"] = stopped

         if (disabled)
            statsMap["${lb}.DISABLED"] = disabled

         if (total)
            statsMap["${lb}.TOTAL"] = total

        }

         if (!statsMap) {
             render "no statistics available for lbname: ${lbname}"
             return
         }

         render(view:"stats", model: [statsMap:statsMap])
    }

    def health = {

        def healthMap = [:]
        def loadBalancerList = []
        def loadBalancer = params?.lbname
        def netmask = params?.netmask ?: null

        if (loadBalancer) {
            loadBalancerList << loadBalancer.toString().toLowerCase()
        } else {
            loadBalancerList = ModJkLoadbalancer.list().collect { it.name }.unique()
        }

        loadBalancerList.each { lb ->

            def activeOK = jkService.searchLoadBalancer(lb,"ACT","OK", netmask)
            def activeTotal = jkService.searchLoadBalancer(lb,"ACT","", netmask)

            if (activeOK && activeTotal) {
               def percentOK = jkService.searchLoadBalancer(lb,"ACT","OK", netmask) / jkService.searchLoadBalancer(lb,"ACT","", netmask) * 100
                healthMap[lb] = percentOK.stripTrailingZeros().toPlainString()
            }


        }

        if (!healthMap) {
            render "no health information can be calculated for ${loadBalancer}"
            return
        }

        render(view: "health", model:[healthMap:healthMap])



    }



}
