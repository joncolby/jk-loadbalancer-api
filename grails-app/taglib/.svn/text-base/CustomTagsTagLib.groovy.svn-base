
class CustomTagsTagLib {
	
	static namespace = "jklb"
	
	def getWorker = { attrs ->
	
	def id = null 
	
	if ( attrs.id )
			id = attrs.remove('id')
			
		def worker = ModJkWorker.get(id)

		out <<  worker
		
		
	}
	
	
	
def getStateCount = { attrs ->
	
	def name = null 
	def lb = null
	def state = null 
	def total
	if ( attrs.name && attrs.state )
			name = attrs.remove('name')
			state = attrs.remove('state')
			
		 def c = ModJkWorker.createCriteria()
			
			total = c.count {
					loadbalancer {
							eq("name", name)
					}
					and {
						   like("state", "%$state%")
					}
			}
	
		out <<  total
		
		
	}


  def getStateCountByLb = { attrs ->

	def id = null
	def state = null
	def total
	if ( attrs.id && attrs.state )
			id = attrs.remove('id')
			state = attrs.remove('state')

		 def c = ModJkWorker.createCriteria()

			total = c.count {
					loadbalancer {
						  idEq(id)
					}
					and {
						  like("state", "%$state%")
					}
			}

		out <<  total


	}
	
	
def getActivationCount = { attrs ->
	
	def name = null 
	def lb = null
	def activation = null 
	def total = null
	
	if ( attrs.name && attrs.activation )
			name = attrs.remove('name')
			activation = attrs.remove('activation')

			
		def c = ModJkWorker.createCriteria()
			
			total = c.count {
					loadbalancer {
							eq("name", "$name")
					}
					and {
						   eq("activation", "$activation")
					}
			}
			
		//def c = ModJkWorker.countByLoadbalancerAndActivation(lb, activation)
		out <<  total

	}

  def getActivationCountByLb = { attrs ->

	def id = null
	def activation = null
	def total = null

	if ( attrs.id && attrs.activation )
			id = attrs.remove('id')
			activation = attrs.remove('activation')


		def c = ModJkWorker.createCriteria()

			total = c.count {
					loadbalancer {
							idEq(id)
					}
					and {
						   eq("activation", "$activation")
					}
			}

		//def c = ModJkWorker.countByLoadbalancerAndActivation(lb, activation)
		out <<  total

	}
	
def getCount = { attrs ->
	
	def name = null 
	def lb = null
    def total = null
    
	if ( attrs.name  )
			name = attrs.remove('name')
			
			def c = ModJkWorker.createCriteria()
			
			total = c.count {
					loadbalancer {
							eq("name", "$name")
					}
}

		//def c = ModJkWorker.countByLoadbalancerLike(name)
		out <<  total

	}


  def getCountByLb = { attrs ->

	def id = null
    def total = null

	if ( attrs.id  )
			id = attrs.remove('id')

			def c = ModJkWorker.createCriteria()

			total = c.count {
					loadbalancer {
							idEq(id)
					}
            }

		out <<  total

	}
	


}
