
# clj-image-tools

### Overview

The <strong>clj-image-tools</strong> is a set of image file manipulating tools for Clojure projects.

### deps.edn

```
{:deps {bithandshake/clj-image-tools {:git/url "https://github.com/bithandshake/clj-image-tools"
                                      :sha     "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"}}
```

### Current version

Check out the latest commit on the [release branch](https://github.com/bithandshake/clj-image-tools/tree/release).

### Documentation

The <strong>clj-image-tools</strong> functional documentation is [available here](https://bithandshake.github.io/clj-image-tools).

### Changelog

You can track the changes of the <strong>clj-image-tools</strong> library [here](CHANGES.md).

# Usage

> Some parameters of the following functions and some further functions are not discussed in this file.
  To learn more about the available functionality, check out the [functional documentation](documentation/COVER.md)!

### Index

- [How to generate a thumbnail for an image?](#how-to-generate-a-thumbnail-for-an-image)

### How to generate a thumbnail for an image?

The [`image.api/generate-thumbnail!`](documentation/clj/image/API.md/#generate-thumbnail)
function generates a thumbnail for an image.

- If the output directory doesn't exist, the function creates it.

```
(generate-thumbnail! "my-image.png" "my-thumbnail.png" {:max-size 512})
```
