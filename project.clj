(defproject quiz-api "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]
                 [ring/ring-json "0.2.0"]
                 [org.clojure/java.jdbc "0.3.0"]
                 [com.oracle/jdbc_11g "11.2.0.3.0"]
                 [environ "0.4.0"]
                 [yesql "0.3.0-SNAPSHOT"]]
  :plugins [[lein-ring "0.8.8"]]
  :ring {:handler quiz-api.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}}

	:repositories
		[["guardian-third-party" "http://nexus.gudev.gnl:8081/nexus/content/repositories/thirdparty"]])
