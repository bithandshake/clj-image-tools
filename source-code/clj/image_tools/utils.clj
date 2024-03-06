
(ns image-tools.utils
    (:import [java.awt AlphaComposite]
             [java.awt Image]
             [java.awt.image BufferedImage]
             [java.io File]
             [javax.imageio ImageIO]
             [javax.imageio IIOImage]
             [javax.imageio ImageWriteParam]
             [javax.imageio.plugins.jpeg JPEGImageWriteParam]
             [javax.imageio.stream FileImageOutputStream]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn image-dimensions
  ; @ignore
  ;
  ; @param (java.awt.image.BufferedImage object) image
  ;
  ; @usage
  ; (image-dimensions ...)
  ; =>
  ; [1024 768]
  ;
  ; @return (integers in vector)
  ; [(integer) width
  ;  (integer) height]
  [image]
  [(-> image .getWidth)
   (-> image .getHeight)])

(defn scale-dimensions
  ; @ignore
  ;
  ; @param (integers in vector) image-dimensions
  ; @param (integers in vector) scaled-dimensions
  ;
  ; @usage
  ; (scale-dimensions [1200 600] [400 150])
  ; =>
  ; [300 150]
  ;
  ; @usage
  ; (scale-dimensions [400 150] [1200 600])
  ; =>
  ; [1200 450]
  ;
  ; @return (integers in vector)
  ; [(integer) width
  ;  (integer) height]
  [[image-width image-height] [scaled-width scaled-height]]
  (let [ratio (max (/ image-width  scaled-width)
                   (/ image-height scaled-height))]
       [(/ image-width ratio) (/ image-height ratio)]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn resize-image
  ; @ignore
  ;
  ; @param (java.awt.image.BufferedImage object) input
  ; @param (map) options
  ; {:max-height (px)
  ;  :max-width (px)
  ;  :type-int (integer)}
  ;
  ; @return (java.awt.image.BufferedImage object)
  [input {:keys [max-height max-width type-int]}]
  (let [[input-width  input-height]  (image-dimensions input)
        [output-width output-height] (scale-dimensions [input-width input-height] [max-width max-height])
        output    (new BufferedImage output-width output-height type-int)
        graphics  (.createGraphics    output)
        temporary (.getScaledInstance input output-width output-height Image/SCALE_SMOOTH)]
       (.drawImage graphics temporary 0 0 nil)
       (.dispose   graphics)
       (->         output)))
