
(ns image-tools.api
    (:require [image-tools.side-effects :as side-effects]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (image-tools.side-effects)
(def generate-thumbnail! side-effects/generate-thumbnail!)
