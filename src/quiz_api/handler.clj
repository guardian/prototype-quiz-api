(ns quiz-api.handler
  (:use [compojure.core])
  (:require
	[compojure.handler :as handler]
	[compojure.route :as route]
	[ring.middleware.json :as json-wrapper]
	[clojure.java.jdbc :as j]
	[environ.core :as env]
	[yesql.core :refer [defquery defqueries]]))

(def r2-db {:subprotocol "oracle"
	:classname "oracle.jdbc.OracleDriver"
	:subname (env/env :r2-db-connect)
	:user (env/env :r2-db-user)
	:password (env/env :r2-db-password)})

(defqueries "quiz_api/sql/queries.sql")

(defn add-answers [question]
	(assoc question :answers (find-all-answers r2-db (:id question))))

(defn read-quiz-data [quiz-id]
	(let [quiz-data (first (quiz-by-pk r2-db quiz-id))
		bands (bands-for-quiz r2-db quiz-id)
		questions (questions-for-quiz r2-db quiz-id)]
		(assoc quiz-data :questions (map add-answers questions)
			:bands bands)))

(defroutes app-routes
  (GET "/" [] {:body {:hello "world" :db-user (env/env :r2-db-user)}})
  (GET "/quiz/:id" [id] {:body (read-quiz-data id)})
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
	(->
		(handler/site app-routes)
		(json-wrapper/wrap-json-response)))
