(defproject gorilla-tools "0.1.0-SNAPSHOT"
  :description "Usefull tools for the Gorilla Repl"
  :url "https://github.com/behrica/gorilla-tools"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.taoensso/nippy "2.7.1" ]
                 [net.mikera/core.matrix "0.31.1"]
                 ]


  :profiles {:dev {:dependencies [[midje "1.6.3"]]}}
  )
