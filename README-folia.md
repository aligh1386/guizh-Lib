# Folia support patch (guizhanlib)

## What was broken

`guizhanlib-slimefun`'s `Scheduler` class (used by every addon that extends
`AbstractAddon`) called the legacy `Bukkit.getScheduler().runTask*` methods.
On Folia these throw `UnsupportedOperationException` for anything sync
(`run`, `run(delay, ...)`, `repeat`, `repeatAsync`'s legacy-tick timing).

Worse: `AbstractAddon#onEnable()` unconditionally calls
`scheduler.repeat(Slimefun.getTickerTask().getTickRate(), ...)` to keep a
tick counter. On Folia this crashes **immediately on enable**, for every
plugin built on this library (including GuizhanLibPlugin itself).

## What was fixed

`guizhanlib-slimefun/src/main/java/net/guizhanss/guizhanlib/slimefun/addon/Scheduler.java`
was rewritten to use Paper's region-based scheduler API instead of the
legacy `BukkitScheduler`:

- `Bukkit.getGlobalRegionScheduler()` for sync `run` / `run(delay)` / `repeat`
- `Bukkit.getAsyncScheduler()` for `runAsync` / `runAsync(delay)` / `repeatAsync`

These APIs are part of the Paper API itself (not Folia-only), so this works
identically and correctly on regular Paper *and* on Folia — no extra
dependency (e.g. FoliaLib) was needed, since none of these tasks are tied to
a specific world/location (they're global, e.g. the tick counter).

No other file in the repo used the legacy scheduler in a way that needed
changing.

## How to build & use this patch

The official `net.guizhanss:guizhanlib-all` artifact on Maven Central does
not yet contain this fix. Until it's merged upstream
(https://github.com/ybw0014/guizhanlib), build and use it locally:

```bash
# from this guizhanlib project root
./gradlew publishToMavenLocal
```

This publishes `net.guizhanss:guizhanlib-all:2.5.0-folia-local` (version
bumped in `gradle.properties` so it doesn't collide with the real release)
into your local `~/.m2` repository.

`GuizhanLibPlugin`'s `build.gradle.kts` has already been updated to:
- add `mavenLocal()` to its repositories
- depend on `net.guizhanss:guizhanlib-all:2.5.0-folia-local`
- declare `foliaSupported = true` in its `bukkit {}` block

So building `GuizhanLibPlugin` after the step above will produce a
Folia-compatible jar.

## Recommended follow-up

Open a PR against https://github.com/ybw0014/guizhanlib with this
`Scheduler.java` change so the fix ships in an official release, then
switch `GuizhanLibPlugin` back to the published Maven Central version.
