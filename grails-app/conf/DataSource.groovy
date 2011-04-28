

 /**  
dataSource {
	pooled = true
	driverClassName = "org.hsqldb.jdbcDriver"
	username = "sa"
	password = ""
}
 **/

hibernate {
    cache.use_second_level_cache=true
    cache.use_query_cache=true
    //cache.provider_class='com.opensymphony.oscache.hibernate.OSCacheProvider'
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'

    
}


//hibernate {
//    cache.use_second_level_cache = true
//    cache.use_query_cache = true
//    cache.provider_class = 'com.googlecode.hibernate.memcached.MemcachedCacheProvider'
//    memcached {
//        servers = "localhost:11211"
//    }
//}

// environment specific settings
environments {
	development {
		dataSource {

				logSql = false
				loggingSql = false    
			   //dbCreate = "create-drop" // one of 'create', 'create-drop','update'
			   
			   // LOADED FROM PROPERTIES FILE
			   /*
        	    dbCreate = "update"
			    url = "jdbc:mysql://multidb38.mobile.rz:3306/jkloadbalancer?autoReconnect=true"
			    driverClassName = "com.mysql.jdbc.Driver"
			    username = "loadbalancer"
			    password = "loadbalancer"
			    dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
			    loggingSql = false
			    pooling=true
			    */
           
        }
	}
	test {
		dataSource {
			dbCreate = "update"
			url = "jdbc:hsqldb:mem:testDb"
		}
	}
	production {
		dataSource {

			logSql = false
			loggingSql = false    
			//dbCreate = "create-drop"
			//url = "jdbc:hsqldb:file:prodDb;shutdown=true"
			//url = "jdbc:mysql://localhost:3306/loadbalancer?autoReconnect=true"
					
			// LOADED FROM PROPERTIES FILE
			  
			  /*
			dbCreate = "update"
			url = "jdbc:mysql://multidb38.mobile.rz:3306/loadbalancer?autoReconnect=true"
		    driverClassName = "com.mysql.jdbc.Driver"
		    username = "loadbalancer"
		    password = "loadbalancer"
		    dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
		    loggingSql = false
			pooling=true
			*/
			
		}
	}
}
