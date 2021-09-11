(ns cljb.main
  (:require [sci.core :as sci]))

(defn my-eval [s]
  (let [opts {:classes {'js goog/global :allow :all}
              :namespaces {'clojure.core {'println println}}}]
    (sci/eval-string s opts)))

(defn on-document-load []
  (doseq [e (.getElementsByTagName js/document "script")]
    (when (= "text/cljs" (.getAttribute e "type"))
      (my-eval (.-textContent e)))))

(defn -main [& _]
  (.addEventListener js/window "load" on-document-load))
