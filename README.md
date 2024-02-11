# cljb

Browser support for ClojureScript.

## What is cljb?

`cljb` is a tiny JavaScript shim that allows you to run ClojureScript directly in the browser without the need for local build tools.

## Why is it called cljb?

`cljb` is short for "**cl**o**j**ure in the **b**rowser".

## Why is cljb relevant?

I'm not sure it is, but I think it's pretty neat :)

I've always found it strange that compile-to-js languages are your only option for building web apps. A web browser is more than a compilation target - it's is own environment with great support for runtime debugging and interactivity. There's great potential for interactive development but many of us choose to use other languages, libraries and frameworks that shift the development experience from happening within the browser to a hybrid of browser + external tools. I don't think it has to be this way and `cljb` is a PoC of what an alternative to a JS-only browser could look like.

# How do I use it?

You first have to make the `cljb` runtime available in your web app:

```html
<head>
  ...
  <script src="https://<the-path-to-the-hosted-shim>.js"></script>
  ...
</head>
```

You then have the option to either include `.cljb` files just as you would any other `.js` file in your web app:

```html
<script src="/path/to/my/web_app.cljb"></script>
```

Or you can inline `cljb` code just as you would with JS code:

```html
<script type="application/cljb">
  (defn say-hello [name]
    (println "Hello" name))

  (say-hello "Frodo")
</script>
```

# How does it work?

`cljb` includes the ClojureScript runtime, prebundled using the fantastic Clojure compiler [sci](https://github.com/babashka/sci), alongside a default set of libraries that I think many web app developers will find useful.

When `cljb` is included in your web app it will wait for the page to load, find all external and inlined `cljb` code and execute it within the `cljb` runtime in the order that it appears on the page.

## Bundled libraries

These come pre-bundled with `cljb`, lmk if you'd like to see another library on this list.

* `re-frame`
* `reagent`

# Development

`cljb`'s source code is located under `src/` and example code under `public/`.

Start by running:

```sh
bun start
```

This will auto compile `cljb`'s source code whenever it's edited and start a web server on `localhost:3000` that serves the `public/` folder.

Navigate to `http://localhost:3000` and start playing around.
