(ns cljb.main
  (:require [cljs.js :refer [empty-state eval-str js-eval]]))

(defn my-eval [s]
  (eval-str
   (empty-state)
   s
   nil
   {:eval       js-eval
    :source-map true
    :context    :expr}
   #()))

(do
  (def a 1)
  (eval (read-string "(+ a 2)")))

(defn on-document-load []
  (let [script-elems (.getElementsByTagName js/document "script")]
    (doseq [e script-elems]
      (when (= "text/cljs" (.getAttribute e "type"))
        (-> e
            (.-textContent)
            (my-eval))))))

(defn -main [& _]
  (.addEventListener js/window "load" on-document-load))
