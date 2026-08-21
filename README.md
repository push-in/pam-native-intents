# PAM Native Intents

## Start here

This is a Composer extension for PAM Native. Install the PAM Runtime, create a native project, and then add this package through PAM’s verified Composer toolchain:

```bash
curl --proto '=https' --proto-redir '=https' --tlsv1.2 \
    --connect-timeout 15 --max-time 60 --max-filesize 1048576 -fsSL \
    https://github.com/push-in/pam/releases/latest/download/install.sh | sh

pam init my-app --template native
cd my-app
pam composer require pushinbr/pam-native-intents
pam doctor --fix
```


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

This package targets PAM Native `0.6.x`, Android API 26+, and iOS 15+ unless a platform-specific section above states a stricter requirement. Platform SDKs, credentials, entitlements, physical hardware, and store configuration remain application responsibilities.

- [PAM documentation](https://push-in.github.io/pam-docs/introduction/)
- [PAM Native overview](https://push-in.github.io/pam-docs/native/overview/)
- [Plugin and native capability model](https://push-in.github.io/pam-docs/native/plugins/)
- [Report an issue](https://github.com/push-in/pam-native-intents/issues)

Security vulnerabilities should be reported through the repository security policy or GitHub private vulnerability reporting, not a public issue.
