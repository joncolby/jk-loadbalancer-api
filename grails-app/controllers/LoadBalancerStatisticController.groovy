

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

        def statsArray = []
        def statsMap = [:]

        loadBalancerList.each { lb ->
            statsMap = [:]
         def active = jkService.searchLoadBalancer(lb,"ACT","", netmask)
         def activeError = jkService.searchLoadBalancer(lb,"ACT","ERR",netmask)
         def activeOK = jkService.searchLoadBalancer(lb,"ACT","OK", netmask)
         def stopped = jkService.searchLoadBalancer(lb,"STP","", netmask)
         def disabled = jkService.searchLoadBalancer(lb,"DIS","", netmask)
         def total = jkService.searchLoadBalancer(lb,"","", netmask)

         statsMap["${lb}.LBNAME"] = lb
         statsMap["${lb}.ACTIVE"] = active ?: 0
         statsMap["${lb}.ACTIVE.ERROR"] = activeError ?: 0

            if (active && activeError) {
                def percentActiveError = activeError / active * 100
                statsMap["${lb}.ACTIVE.ERROR.PERCENT"] = percentActiveError.stripTrailingZeros().toPlainString()
            }

         statsMap["${lb}.ACTIVE.OK"] = activeOK ?: 0

            if ( active && activeOK) {
                def percentActiveOk = activeOK / active * 100
                statsMap["${lb}.ACTIVE.OK.PERCENT"] = percentActiveOk.stripTrailingZeros().toPlainString()
            } else {
              statsMap["${lb}.ACTIVE.OK.PERCENT"] = 0
            }

            statsMap["${lb}.STOPPED"] = stopped ?: 0
            statsMap["${lb}.DISABLED"] = disabled ?: 0
            statsMap["${lb}.TOTAL"] = total ?: 0

            if (total > 0) {
                statsMap["${lb}.TOTAL.OK"] = (total - ( stopped + disabled )) / total * 100
            } else {
                statsMap["${lb}.TOTAL.OK"] = 0
            }
            statsArray << statsMap
        }

         if (!statsMap) {
             render "no statistics available for lbname: ${lbname}"
             return
         }

         //render(view:"stats", model: [statsMap:statsMap])
         render(view:"stats", model: [statsArray:statsArray])
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
