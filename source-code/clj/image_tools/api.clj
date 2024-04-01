
(ns image-tools.api
    (:require [image-tools.side-effects :as side-effects]
              [image-tools.utils :as utils]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (image-tools.side-effects/*)
(def generate-thumbnail! side-effects/generate-thumbnail!)

; @redirect (image-tools.utils/*)
(def image-dimensions utils/image-dimensions)
(def scale-dimensions utils/scale-dimensions)
