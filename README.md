# PAM Native Intents

Publishes named PAM routes as Android Dynamic Shortcuts and Apple App Intents/App Shortcuts. Actions use deep links so the normal named-route lifecycle remains the single source of navigation truth.

```bash
composer require pushinbr/pam-native-intents
pam mobile prepare
```

```php
$intents->register([
    new IntentAction('inbox', 'Open inbox', 'myapp://inbox', 'Unread messages'),
], $complete);
```

iOS uses the shared `group.<application-id>.pam-native` container. Keep titles stable: users may build automations that outlive an application update.
