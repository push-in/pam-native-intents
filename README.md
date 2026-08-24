<!-- pam:product-page:start -->
<div align="center">

# PAM Native Intents

**Make application routes visible to the operating system.**

Publish typed PAM routes as Android shortcuts and Apple App Intents while keeping deep-link inputs bounded and validated.

[![Latest version](https://img.shields.io/packagist/v/pushinbr/pam-native-intents?style=flat-square&label=stable)](https://packagist.org/packages/pushinbr/pam-native-intents)
[![CI](https://img.shields.io/github/actions/workflow/status/push-in/pam-native-intents/ci.yml?branch=main&style=flat-square&label=CI)](https://github.com/push-in/pam-native-intents/actions)
![PHP](https://img.shields.io/badge/PHP-8.5-777BB4?style=flat-square&logo=php&logoColor=white)
![Android](https://img.shields.io/badge/Android-API%2026%2B-3DDC84?style=flat-square&logo=android&logoColor=white)
![iOS](https://img.shields.io/badge/iOS-15%2B-000000?style=flat-square&logo=apple&logoColor=white)

**[Documentation](https://push-in.github.io/pam-docs/native/overview/) · [Quick start](#quick-start) · [What you can build](#what-you-can-build) · [PAM ecosystem](https://push-in.github.io/pam-docs/ecosystem/) · [Issues](https://github.com/push-in/pam-native-intents/issues)**

</div>

---

## Why PAM Native Intents

Publish typed PAM routes as Android shortcuts and Apple App Intents while keeping deep-link inputs bounded and validated. The public API is strictly typed for PHP 8.5; expensive or frame-sensitive work stays in Rust or the platform SDK instead of crossing the application boundary every frame.

| | |
| --- | --- |
| **Best for** | A focused capability you can add to any PAM Native application |
| **Native path** | Android Shortcuts · App Intents |
| **Application model** | Composer package + generated native integration |
| **Design rule** | Independent module; no feed, vertical, or application template bundled |

## What you can build

- Siri and system search actions
- Android dynamic shortcuts
- Contextual entry points into specific application routes

## Quick start

Already have a PAM Native project? Add only this capability:

```bash
pam composer require pushinbr/pam-native-intents
pam doctor --fix
```

New to PAM? Follow the **[five-minute PAM Native setup](https://push-in.github.io/pam-docs/native/overview/)** once, then return here. Your application stays a normal Composer project with a committed lockfile.
<!-- pam:product-page:end -->

## See it in action

Publishes named PAM routes as Android Dynamic Shortcuts and Apple App Intents/App Shortcuts. Actions use deep links so the normal named-route lifecycle remains the single source of navigation truth.

```bash
pam add intents
pam doctor
```

```php
$intents->register([
    new IntentAction('inbox', 'Open inbox', 'myapp://inbox', 'Unread messages'),
], $complete);
```

iOS 18+ uses the shared `group.<application-id>.pam-native` container. Apple requires
the `OpenURLIntent` destination to be an HTTPS universal link associated with the app;
custom URL schemes remain supported by Android Dynamic Shortcuts. Keep titles stable:
users may build automations that outlive an application update.

## What installation does

`pam add intents` resolves the official compatible package, performs a non-mutating Composer preflight, updates the normal `composer.json` and `composer.lock`, refreshes generated native integration when required, and leaves the project ready for `pam doctor` validation.

Use `pam packages` to inspect availability and `pam remove intents` to uninstall the capability safely. Direct Composer commands are an advanced interoperability path; PAM is the supported application workflow.

## API guide

| API | Responsibility |
| --- | --- |
| `Intents` | Publish and remove native app actions. |
| `IntentAction` | Describe a stable action identifier, title, route, and subtitle. |

All coded states, kinds, and variants are sequential integer-backed enums. Use enum cases in application code; do not depend on raw wire numbers.

## Production checklist

- Keep action identifiers and titles stable across releases.
- Route all actions through the app's normal named-route lifecycle.
- Use associated HTTPS universal links for Apple OpenURLIntent destinations.
- Run `pam doctor`, `pam test`, and a signed release build on every supported platform.
- Exercise denial, cancellation, backgrounding, process restart, and offline behavior before release.

## Troubleshooting

- **An iOS action is absent:** verify the shared app group, deployment target, and associated domain.
- **Android shortcut opens the wrong screen:** test the deep link through the normal router first.
- **Old titles remain:** operating systems may cache user-selected shortcuts.
- **Native integration is stale:** run `pam doctor --fix`, rebuild the native host, and inspect the first reported diagnostic.

## Compatibility and support

This package targets PAM Native `0.8.x`, Android API 26+, and iOS 15+ unless a platform-specific section above states a stricter requirement. Platform SDKs, credentials, entitlements, physical hardware, and store configuration remain application responsibilities.

- [PAM documentation](https://push-in.github.io/pam-docs/introduction/)
- [PAM Native overview](https://push-in.github.io/pam-docs/native/overview/)
- [Plugin and native capability model](https://push-in.github.io/pam-docs/native/plugins/)
- [Report an issue](https://github.com/push-in/pam-native-intents/issues)

Security vulnerabilities should be reported through the repository security policy or GitHub private vulnerability reporting, not a public issue.
