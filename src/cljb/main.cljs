(ns cljb.main
  (:require [reagent.core :as r]
            [reagent.dom :as rd]
            [sci.core :as sci]))

(declare my-eval)

(defn prompt []
  (my-eval (js/prompt "Insert cljs here")))

(def ctx
  (sci/init {:classes {'js goog/global :allow :all}
             :namespaces {'reagent.core {'atom r/atom}
                          'reagent.dom {'render rd/render}
                          'clojure.core {'println println
                                         'prompt prompt}}}))

(defn my-eval [s]
  (sci/eval-string* ctx s))

(defn on-document-load []
  (doseq [e (.getElementsByTagName js/document "script")]
    (when (= "text/cljs" (.getAttribute e "type"))
      (my-eval (.-textContent e)))))

(defn -main [& _]
  (.addEventListener js/window "load" on-document-load))
