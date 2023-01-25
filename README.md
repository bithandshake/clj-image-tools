
# clj-image

### Overview

The <strong>clj-image</strong> is a set of Clojure image handler functions
(e.g. thumbnail-generator, ...)

### deps.edn

```
{:deps {bithandshake/clj-image {:git/url "https://github.com/bithandshake/clj-image"
                                :sha     "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"}}
```

### Current version

Check out the latest commit on the [release branch](https://github.com/bithandshake/clj-image/tree/release).

### Documentation

The <strong>clj-image</strong> functional documentation is [available here](documentation/COVER.md).

### Changelog

You can track the changes of the <strong>clj-image</strong> library [here](CHANGES.md).

### Index

- [How to generate a thumbnail for an image?](#how-to-generate-a-thumbnail-for-an-image)

# Usage

### How to generate a thumbnail for an image?

The [`image.api/generate-thumbnail!`](documentation/clj/image/API.md/#generate-thumbnail)
function generates a thumbnail for an image.

- If the output directory doesn't exist, the function creates it.

```
(generate-thumbnail! "my-image.png" "my-thumbnail.png" {:max-size 512})
```
