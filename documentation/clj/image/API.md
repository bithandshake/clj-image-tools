
# <strong>image.api</strong> namespace

<strong>[README](../../../README.md) > [DOCUMENTATION](../../COVER.md) > </strong>source-code/clj/image/api.clj

### generate-thumbnail!

```
@param (string) input-path
@param (string) output-path
@param (map) options
{:max-size (px)}
```

```
@usage
(generate-thumbnail! "my-file.png" "my-thumbnail.png" {:max-size 512})
```

<details>
<summary>Source code</summary>

```
(defn generate-thumbnail!
  [input-path output-path {:keys [max-size] :as options}]
  (let [input       (-> input-path clojure.java.io/file ImageIO/read)
        input-width (-> input .getWidth)
        mime-type   (io/filepath->mime-type input-path)
        type-int    (case mime-type "image/png" BufferedImage/TYPE_INT_ARGB BufferedImage/TYPE_INT_RGB)
        output      (helpers/resize-image input {:max-height max-size :max-width max-size :type-int type-int})
        [output-width output-height] (helpers/image-dimensions output)
        temporary (new BufferedImage output-width output-height type-int)
        graphics  (.getGraphics temporary)]
       (.drawImage graphics output 0 0 nil)
       (.dispose   graphics)
       (save-thumbnail! temporary output-path {:quality 1.0})
       (clojure.java.io/file output-path)))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [image.api :refer [generate-thumbnail!]]))

(image.api/generate-thumbnail! ...)
(generate-thumbnail!           ...)
```

</details>
