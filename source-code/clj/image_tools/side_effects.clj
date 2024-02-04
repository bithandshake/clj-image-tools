
(ns image-tools.side-effects
    (:import [java.awt.image BufferedImage]
             [java.io File]
             [javax.imageio IIOImage]
             [javax.imageio ImageIO]
             [javax.imageio ImageWriteParam]
             [javax.imageio.plugins.jpeg JPEGImageWriteParam]
             [javax.imageio.stream FileImageOutputStream])
    (:require [image-tools.utils :as utils]
              [io.api            :as io]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn save-thumbnail!
  ; @ignore
  ;
  ; @description
  ; Saves the given image object as image to the given output filepath.
  ;
  ; @usage
  ; (save-thumbnail ... "my-thumbnail.png" {...})
  ;
  ; @param (java.awt.image.BufferedImage object) input
  ; @param (string) output-path
  ; @param (map) options
  ; {:quality (number)}
  [input output-path {:keys [quality]}]
  (let [jpeg-params   (new JPEGImageWriteParam nil)
        extension     (io/filepath->extension output-path)
        writer        (-> extension ImageIO/getImageWritersByFormatName .next)
        output-file   (clojure.java.io/file output-path)
        output-stream (new FileImageOutputStream output-file)]
       (.setCompressionMode    jpeg-params ImageWriteParam/MODE_EXPLICIT)
       (.setCompressionQuality jpeg-params quality)
       (.setOutput writer output-stream)
       (.write     writer nil (new IIOImage input nil nil) jpeg-params)))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn generate-thumbnail!
  ; @description
  ; Reads the image from the given input filepath, resizes the read image and writes
  ; the output on the give output filepath.
  ;
  ; @param (string) input-path
  ; @param (string) output-path
  ; @param (map) options
  ; {:max-size (px)}
  ;
  ; @usage
  ; (generate-thumbnail! "my-image.png" "my-thumbnail.png" {:max-size 512})
  [input-path output-path {:keys [max-size] :as options}]
  (let [input       (-> input-path clojure.java.io/file ImageIO/read)
        input-width (-> input .getWidth)
        mime-type   (io/filepath->mime-type input-path)
        type-int    (case mime-type "image/png" BufferedImage/TYPE_INT_ARGB BufferedImage/TYPE_INT_RGB)
        output      (utils/resize-image input {:max-height max-size :max-width max-size :type-int type-int})
        [output-width output-height] (utils/image-dimensions output)
        temporary (new BufferedImage output-width output-height type-int)
        graphics  (.getGraphics temporary)]
       (io/create-path! output-path)
       (.drawImage graphics output 0 0 nil)
       (.dispose   graphics)
       (save-thumbnail! temporary output-path {:quality 1.0})
       (clojure.java.io/file output-path)))
