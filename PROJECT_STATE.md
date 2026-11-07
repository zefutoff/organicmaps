# PROJECT_STATE — Organic Maps / Wear OS

> **État arrêté au 7 novembre 2026 (Europe/Paris)**  
> **Statut : VALIDÉ ET PUBLIÉ LE 07/11/2026 — instantané historique, à revérifier avant toute action**  
> Emplacement de suivi : branche dédiée `project-tracking` du fork `zefutoff/organicmaps`, fichier racine `PROJECT_STATE.md`. La branche par défaut du fork est `master` : ne pas y ajouter cette note afin de ne pas l'entraîner dans une future PR upstream. **Ne jamais l'ajouter au dépôt `organicmaps/organicmaps` sans décision explicite.**


## Résumé opérationnel — 07/11/2026

- **PR prioritaire :** [organicmaps/organicmaps#13533](https://github.com/organicmaps/organicmaps/pull/13533), toujours **ouverte**, approuvée par `strider-dunadan` et `biodranik` ; **ne pas la qualifier de fusionnée**.
- **HEAD vérifié :** `1131fb667b9974f2e43c34613a52e9823828a3fe`. Ce SHA est postérieur à l'ancienne référence locale ; comparer le distant avant tout push/rebase.
- **CI :** 6 workflows GitHub Actions et 8 jobs réussis au HEAD. Contrôle DCO indépendant non confirmé par l'audit.
- **Reviews :** 3 fils encore marqués non résolus, sans nouveau correctif technique clairement exigé dans le présent audit.
- **Correctif déjà intégré :** `API_NOT_CONNECTED` (#14114) dans #13533 ; l'issue demeure ouverte tant que la fusion n'a pas eu lieu.
- **Prochaine fonctionnalité :** [#14115](https://github.com/organicmaps/organicmaps/issues/14115) (service Wear et Ongoing Activity), dans une PR indépendante après confirmation de sa base.
- **Limite :** état du répertoire de travail local inconnu ; ce document est un instantané, à comparer aux faits nouveaux seulement.


## 0. Convention de preuve et limites

- **[GH]** : constat directement vérifié sur GitHub lors de l'audit du 07/11/2026 (PR, issue, discussion, commit, workflow ou fichier du dépôt).
- **[HIST]** : résultat consigné dans nos conversations précédentes ou dans la description de la PR ; **pas réexécuté** à la date de cet audit.
- **[À VÉRIFIER]** : donnée absente ou non accessible depuis les sources consultées, à ne pas présenter comme acquise.
- Les SHA et statuts sont une **photographie datée**, pas une vérité durable. À chaque reprise, comparer le HEAD distant et la date du dernier événement avant de décider une action.
- Cet audit ne dispose **pas** d'un accès à l'arbre de travail local du poste de développement. L'état local (`git status`, branches et commits non poussés) est **inconnu** ; ne jamais le déduire de GitHub.

## 1. Référentiels et objectif

| Élément | Référence |
| --- | --- |
| Dépôt upstream | https://github.com/organicmaps/organicmaps |
| Fork contributeur | https://github.com/zefutoff/organicmaps |
| Branche PR | `feature/wearos-navigation-data` dans le fork |
| Cible de la PR | `organicmaps/organicmaps:master` |
| PR principale | https://github.com/organicmaps/organicmaps/pull/13533 |
| Initiative Wear OS | https://github.com/organicmaps/organicmaps/issues/12030 |
| Objectif fonctionnel | Afficher sur la montre les indications de navigation produites par le téléphone (distance / unité jusqu'au virage, prochaine rue, distance restante, temps restant) |
| Destination de cette note | Fork personnel ou espace privé ; **pas upstream** |

## 2. État GitHub actuel de la PR #13533 — [GH, 07/11/2026]

| Champ | Valeur constatée |
| --- | --- |
| Titre | `[android][wear] Publish and render navigation details` |
| État | **OPEN**, non fusionnée, non draft |
| Fusion technique | `mergeable=true` (absence de conflit déduite de la réponse GitHub ; les règles de protection/autorisation de fusion ne sont pas vérifiées séparément) |
| HEAD du fork / de la PR | **`1131fb667b9974f2e43c34613a52e9823828a3fe`** |
| Base upstream lors de la lecture | `0297791ee7b92105655f4573b29c16a02737b825` |
| Ancien HEAD de référence (05/11) | `0d022e736c` — **périmé** |
| Historique annoncé par GitHub | **3 commits** |
| Diff annoncé | **85 fichiers**, +3140 / -146 lignes (inclut de nombreux fichiers de localisation générés) |
| Dernière mise à jour de la PR | **07/11/2026 à 11:18:38 UTC** |
| Approbation 1 | `strider-dunadan`, 06/11/2026 : « LGTM, tested on device, with follow-up PRs » |
| Approbation 2 | `biodranik`, 07/11/2026 : approbation et remerciement |
| Fils de revue | **37 au total ; 3 non clôturés** dans l'interface, voir section 5 |

Le **7 novembre**, `biodranik` a indiqué avoir retiré le drapeau `-r` du générateur, corrigé un autre point mineur et rebasé/mis à jour la PR. Le **message** du HEAD actuel porte un trailer `Signed-off-by` (DCO) au nom du contributeur ; cela **ne constitue pas une vérification de signature cryptographique GPG**. La métadonnée GitHub indique `biodranik` comme committer. **Ne pas faire de force-push depuis une branche locale probablement antérieure sans récupération et comparaison explicites.**

Références : [PR #13533](https://github.com/organicmaps/organicmaps/pull/13533) · [HEAD actuel](https://github.com/organicmaps/organicmaps/commit/1131fb667b9974f2e43c34613a52e9823828a3fe) · [commentaire de biodranik sur le générateur](https://github.com/organicmaps/organicmaps/pull/13533#discussion_r4501465399) · [approbation de strider-dunadan](https://github.com/organicmaps/organicmaps/pull/13533#pullrequestreview-5799655707) · [approbation de biodranik](https://github.com/organicmaps/organicmaps/pull/13533#pullrequestreview-5811884536).

## 3. Historique des contributions : ne pas perdre les décisions déjà prises

| Date | Élément | Résultat / portée |
| --- | --- | --- |
| 20/06/2026 | [#12946](https://github.com/organicmaps/organicmaps/pull/12946) — shell Wear OS minimal | **Fusionnée**. Module Wear, lanceur, protocole minimal ; pas de navigation embarquée à ce stade. |
| 29/06–18/08/2026 | [#13068](https://github.com/organicmaps/organicmaps/pull/13068) — synchronisation de l'état | **Fermée sans fusion** : ne pas comptabiliser cette PR comme livrée. |
| 16/08/2026 | [#13081](https://github.com/organicmaps/organicmaps/pull/13081) — base des modules de communication Wear | **Fusionnée**, maintenue par `biodranik`. |
| 01/09/2026 | [#13362](https://github.com/organicmaps/organicmaps/pull/13362) — DataMap | **Fusionnée**. Abandon du codec JSON de prépublication, séparation protocole indépendant / mapping GMS. |
| 09/09/2026 | [#13460](https://github.com/organicmaps/organicmaps/pull/13460) — réécriture Kotlin | **Fusionnée**. Réécriture des modules Wear sans changement fonctionnel annoncé. |
| 09/09/2026 | Ouverture de [#13533](https://github.com/organicmaps/organicmaps/pull/13533) | Initialement codec/données de navigation, ensuite intégration bout en bout à la demande des reviews. |
| 20/09/2026 | [HIST] Essais réels téléphone + montre | Build Android/Wear réussi ; démarrage/arrêt visibles, mais clignotements/disparitions des indications et verrouillage de la montre **constatés à l'époque**. Ne pas les interpréter comme le comportement du HEAD final. |
| 26–29/10/2026 | Reviews `biodranik` | Revue du cycle de vie, du cache des destinataires, de l'expiration, de la couverture Robolectric et du contrat des capacités. Privilégier des tests ciblés aux frontières Task/Activity. |
| 29/10–02/11/2026 | [HIST] Corrections CI / Kotlin / Robolectric, rebases | Android Web/F-Droid, lint, ktlint et tests redevenus verts après des échecs transitoires et résolution de conflit `data/strings/strings.txt`. Historique ramené à trois commits avec DCO. |
| 03/11/2026 | Retours aux reviews P2 | Retries temporisés sur échecs de registration Capability et Message, tests Robolectric d'échec→récupération et annulation. |
| 05/11/2026 | [#14114](https://github.com/organicmaps/organicmaps/issues/14114) intégré à #13533 | Les exceptions `API_NOT_CONNECTED` n'entraînent plus de boucle d'appels Wear toutes les deux secondes. Deux régressions Robolectric ajoutées. |
| 05/11/2026 | [HIST] HEAD `0d022e736c` | Tests locaux déclarés réussis ; **9/9 checks rapportés alors** ; description GitHub condensée et follow-ups séparés. Cet état est désormais historique. |
| 06/11/2026 | Review finale `strider-dunadan` ; [#14134](https://github.com/organicmaps/organicmaps/issues/14134) | Première approbation sur appareil ; suivi distinct pour déplacer le mapping hors de `NavigationService`. |
| 07/11/2026 | Mise à jour par `biodranik`, puis approbation | HEAD `1131fb66` ; PR ouverte, techniquement fusionnable ; six workflows actuels verts. |

Correction de traduction connexe, **déjà fusionnée hors #13533** : [#14045](https://github.com/organicmaps/organicmaps/pull/14045), le 30/10/2026. Ne pas reproduire ce travail dans les prochaines PR.

## 4. Architecture et décisions techniques validées

1. **Téléphone = source de vérité.** L'état de navigation et les détails proviennent de la logique Android / `NavigationService` et de `RoutingInfo`, indépendamment de la présence d'une Activity carte au premier plan. [Code `NavigationService`](https://github.com/zefutoff/organicmaps/blob/1131fb667b9974f2e43c34613a52e9823828a3fe/android/libs/routing/src/main/java/app/organicmaps/routing/NavigationService.java).
2. **Deux natures de données distinctes.** Le mode persistant `NORMAL`/`NAVIGATION` reste un **DataItem** (`/organicmaps/navigation/state`). Les détails volatils (distance, rue, temps restant) sont transmis par **MessageClient** (`/organicmaps/navigation/details`) ; ne pas refaire les itérations « détails fréquents dans DataItem ». [Contrat](https://github.com/zefutoff/organicmaps/blob/1131fb667b9974f2e43c34613a52e9823828a3fe/android/libs/wear-protocol/src/main/java/app/organicmaps/wear/protocol/WearNavigationData.kt).
3. **Protocole explicite.** Version `1` maintenue pour les extensions compatibles ; keys et formats DataMap fixés, décodage défensif pour valeurs absentes, unités inconnues ou payloads malformés. Unité distincte du nombre (mètres, kilomètres, pieds, miles). [Codec des détails](https://github.com/zefutoff/organicmaps/blob/1131fb667b9974f2e43c34613a52e9823828a3fe/android/libs/wear-protocol-gms/src/main/java/app/organicmaps/wear/protocol/gms/WearNavigationDetailsDataMapCodec.kt).
4. **Routage vers un consommateur réellement actif.** Le téléphone envoie les détails aux montres annonçant la capacité dynamique `organic_maps_active_navigation_watch_ui`. L'Activity n'annonce cette capacité que pendant sa période active ; rétablissement après registration réussie. Le mode persistant reste disponible à la reprise. [Publisher](https://github.com/zefutoff/organicmaps/blob/1131fb667b9974f2e43c34613a52e9823828a3fe/android/sdk/wear/gms/src/main/java/app/organicmaps/sdk/wear/gms/GmsWearNavigationPublisher.kt) · [Activity](https://github.com/zefutoff/organicmaps/blob/1131fb667b9974f2e43c34613a52e9823828a3fe/android/wear/src/main/java/app/organicmaps/wear/MainActivity.kt).
5. **Fraîcheur / coût radio.** Suppression des doublons ; heartbeat **2 secondes** tant que les données source sont fraîches ; invalidation côté téléphone après **30 secondes** sans mise à jour valide de localisation ; effacement des détails affichés sur la montre après **5 secondes** sans message. Ces durées proviennent de la description actuelle de la PR et de ses tests ; ne pas les modifier sans raison mesurée. [Description #13533](https://github.com/organicmaps/organicmaps/pull/13533).
6. **Erreurs transitoires / cycle de vie.** Échec de découverte et de `CapabilityClient.addListener` côté téléphone ou de `MessageClient.addListener` côté montre → retry à **2 s** tant que navigation/Activity l'autorisent ; annulation lors des arrêts/pauses, protection contre callbacks tardifs et résultats d'une ancienne session. **Exception :** `API_NOT_CONNECTED` interdit le retry périodique ; une **nouvelle navigation** peut retenter. [Réponse au mainteneur](https://github.com/organicmaps/organicmaps/pull/13533#discussion_r4484292595) · [tests](https://github.com/zefutoff/organicmaps/blob/1131fb667b9974f2e43c34613a52e9823828a3fe/android/sdk/wear/gms/src/test/java/app/organicmaps/sdk/wear/gms/GmsWearNavigationPublisherRobolectricTest.kt).
7. **Variantes de distribution.** La publication Wear GMS ne se fait pas encore en Google release ; le plan de release reste [#14118](https://github.com/organicmaps/organicmaps/issues/14118). Les builds non-Google passent, mais le mapping Wear exécuté côté `NavigationService` fait des allocations inutiles lorsque le publisher est un no-op : travail séparé [#14134](https://github.com/organicmaps/organicmaps/issues/14134). Ne pas réarchitecturer #13533 sans nouveau retour bloquant.
8. **Localisation et historique.** Les ressources générées suivent `data/strings/strings.txt` et la séparation commits source/ressources voulue en review. `biodranik` a supprimé le `-r` du générateur sur le HEAD actuel afin de ne pas recréer des répertoires de langues non destinées à la release. [Discussion](https://github.com/organicmaps/organicmaps/pull/13533#discussion_r4501465399). La traduction de **deux messages de statut Wear** reste une **autre issue**, [#14116](https://github.com/organicmaps/organicmaps/issues/14116).

### Hors périmètre de #13533, validé comme suivi distinct

- [#14115](https://github.com/organicmaps/organicmaps/issues/14115) : service côté montre + Ongoing Activity pour maintenir le guidage après retour au cadran / écran inactif.
- [#14116](https://github.com/organicmaps/organicmaps/issues/14116) : localisation des deux messages de statut Wear.
- [#14117](https://github.com/organicmaps/organicmaps/issues/14117) : flèche de manœuvre et haptique (haptique écran éteint dépend de #14115).
- [#14118](https://github.com/organicmaps/organicmaps/issues/14118) : intégration Google release, livraison du bundle Wear, piste Play Console, artefacts beta/signatures ; release après #14115 et #14117. Vérifier la coordination éventuelle avec [#13913](https://github.com/organicmaps/organicmaps/pull/13913).
- [#14134](https://github.com/organicmaps/organicmaps/issues/14134) : déplacer le mapping `RoutingInfo → WearNavigationDetails` vers `:sdk:wear:core`, pour découpler `:libs:routing` des types Wear et éviter des allocations inutiles.

## 5. Revue actuelle : n'ouvrir que des actions réelles — [GH]

Les deux **approbations** du 06/11 et du 07/11 sont acquises sur GitHub ; la PR **n'est pas fusionnée**.

**3 fils restent `is_resolved=false` (sur 37)** :

| Fichier / lien | Question | État substantiel |
| --- | --- | --- |
| [`NavigationService.java`](https://github.com/organicmaps/organicmaps/pull/13533#discussion_r4492474400) | Types Wear dans un module routing / variantes non-Google | `strider-dunadan` considère que cela ne casse pas les variantes ; a ouvert **#14134** pour le nettoyage. Fil encore ouvert dans l'interface. |
| [`android/libs/routing/build.gradle`](https://github.com/organicmaps/organicmaps/pull/13533#discussion_r4492480933) | Dépendance `:sdk:wear:core` sur tous les builds | Réponse de `strider-dunadan` : acceptable ; fil encore ouvert. |
| [`tools/unix/generate_localizations.sh`](https://github.com/organicmaps/organicmaps/pull/13533#discussion_r4492527708) | Supprimer l'option `-r` | Fil **outdated** ; `biodranik` déclare avoir lui-même corrigé et rebasé, le 07/11. L'état UI n'a pas été basculé à « résolu ». |

**Interprétation :** un fil non marqué « resolved » n'implique pas nécessairement un blocage technique. Ne pas poster « fixed » ou fermer les fils sans nouvel élément pertinent. Contrôler les règles réelles de fusion avant toute affirmation « prêt à merger automatiquement ».

## 6. Registre des tests et contrôles : ne pas relancer sans nécessité

| Date / niveau de preuve | Validation | Résultat et limite |
| --- | --- | --- |
| 20/09 [HIST] | `:app:assembleGoogleDebug`, `:wear:assembleGoogleDebug` sur machine locale | `BUILD SUCCESSFUL` consigné ; ces builds concernent une **ancienne** version. |
| 20/09 [HIST] | Essai matériel Samsung Galaxy S21 Ultra (Android 15) / Galaxy Watch SM-R930 (Android 16) via ADB | Démarrage/arrêt de route observés ; défauts UI/veille relevés alors. Les cycles de correction ultérieurs ne signifient pas que le problème de retour au cadran a disparu : voir **#14115**. |
| 29/10 [HIST] | Compilation / tests sur modules Wear et contrôles `git diff --check` | Un résultat local `BUILD SUCCESSFUL` de **102 tâches** est consigné. À l'époque, plusieurs dossiers annexes hors PR étaient signalés comme modifiés : ne pas présumer l'arbre local propre aujourd'hui. |
| 29/10–02/11 [HIST] | Robolectric publisher, Wear UI, Android WebDebug/F-Droid, lint, Kotlin formatting, detekt, DCO | Des erreurs intermédiaires puis les corrections sont consignées ; CI **8/8** à cette étape. Ne pas rouvrir les échecs déjà corrigés. |
| 01/11 [HIST] | `:sdk:wear:gms:testDebugUnitTest` et regressions getCapability retry / empty result / cancellation | Exécution locale réussie consignée ; tests intégrés au suivi GitHub. |
| 03/11 [HIST + GH discussion] | Échec→succès `CapabilityClient.addListener`, reprise MessageClient sans nouvel `onResume()`, pause et callbacks tardifs | Ajout annoncé et confirmé par les fichiers de tests présents sur le HEAD ; réponses de revue : [téléphone](https://github.com/organicmaps/organicmaps/pull/13533#discussion_r4459489913), [montre](https://github.com/organicmaps/organicmaps/pull/13533#discussion_r4459491555). |
| 05/11 [HIST + GH code] | Deux regressions Robolectric `API_NOT_CONNECTED` (découverte et registration) | Correctif présent sur le HEAD actuel ; test local déclaré vert, ancien HEAD **`0d022e736c`** : [réponse](https://github.com/organicmaps/organicmaps/pull/13533#discussion_r4484292595), [tests actuels](https://github.com/zefutoff/organicmaps/blob/1131fb667b9974f2e43c34613a52e9823828a3fe/android/sdk/wear/gms/src/test/java/app/organicmaps/sdk/wear/gms/GmsWearNavigationPublisherRobolectricTest.kt). |
| 05/11 [HIST] | Jeu complet de tâches Gradle, chaîne locale | `BUILD SUCCESSFUL` avec **873 tâches** rapporté ; la commande intégrale et les rapports ne sont pas reproduits dans le présent dossier. |
| 05/11 [HIST] | CI ancien HEAD | **9/9 checks déclarés verts** à l'époque ; **ne pas transposer** ce chiffre au HEAD du 07/11. |
| 07/11 [GH] | Six workflows GitHub Actions du SHA `1131fb66` | **6/6 workflows success**. Les workflows représentent **8 jobs GitHub Actions**, tous `success` (voir détail ci-dessous). |
| 07/11 [GH] | Android Check → `Build Android Debug (WebDebug)` | Compilation WebDebug, compilation Wear Google Debug et étape **Run tests** `success` ; émulateur API 35, x86_64, `google_apis`. |
| 07/11 [GH] | Android Check → `Build Android Debug (FdroidDebug)` | Compilation F-Droid Debug `success` ; **Run tests skipped** sur cette matrice, ce n'est pas un test F-Droid exécuté. |
| 07/11 [GH] | Android Check → Android Lint | Job `success`. |
| 07/11 [À VÉRIFIER] | Neuvième check, notamment DCO, sur le nouveau HEAD | La liste des **huit jobs Actions** est entièrement verte, le dernier commit porte `Signed-off-by`, mais l'outil de statut GitHub interrogé n'a renvoyé aucun **commit status** classique. **Ne pas affirmer « 9/9 » comme mesure directe du 07/11.** |

**Commande des tests effectivement définie dans la CI actuelle, et exécutée dans le job WebDebug :**

```bash
./gradlew -P${ARCH} app:test${FLAVOR} sdk:testDebug \
  :wear:testGoogleDebugUnitTest \
  :libs:wear-protocol-gms:testDebugUnitTest \
  :sdk:wear:core:testDebugUnitTest \
  :sdk:wear:gms:testDebugUnitTest \
  sdk:connectedDebugAndroidTest
```

Ici `${ARCH}` et `${FLAVOR}` représentent les valeurs de la matrice du workflow, pas des variables locales supposées préconfigurées. [Workflow à ce SHA](https://github.com/zefutoff/organicmaps/blob/1131fb667b9974f2e43c34613a52e9823828a3fe/.github/workflows/android-check.yaml) · [Exécution Android Check](https://github.com/organicmaps/organicmaps/actions/runs/42367555925).

**Six workflows du HEAD vérifiés comme `success` :**

1. [Validate translation strings](https://github.com/organicmaps/organicmaps/actions/runs/42367555676) — 1 job réussi.
2. [Code style check - Kotlin](https://github.com/organicmaps/organicmaps/actions/runs/42367555102) — 1 job réussi.
3. [Static analysis - Kotlin (detekt)](https://github.com/organicmaps/organicmaps/actions/runs/42367555669) — 1 job réussi.
4. [Code style check](https://github.com/organicmaps/organicmaps/actions/runs/42367555807) — 1 job réussi.
5. [Android SDK Documentation](https://github.com/organicmaps/organicmaps/actions/runs/42367555212) — 1 job réussi.
6. [Android Check](https://github.com/organicmaps/organicmaps/actions/runs/42367555925) — 3 jobs réussis : Android Lint, WebDebug, FdroidDebug.

**Règle anti-répétition :** ne relancer ni le jeu Gradle local complet, ni les tests matériels ni la CI uniquement pour « se rassurer » ; exiger au préalable un changement de HEAD pertinent, un nouveau retour mainteneur, une CI rouge, un défaut reproductible ou un changement d'environnement qui invalide le résultat précédent. Préférer les **tests ciblés** aux batteries complètes. Réexécuter le strict nécessaire lorsque nous commencerons une **nouvelle PR** sur un nouveau HEAD.

## 7. Écarts importants à valider avant toute publication

| Priorité | Écart | Traitement proposé |
| --- | --- | --- |
| P0 | La PR est **approuvée mais ouverte et non fusionnée** | Ne pas la qualifier de « merged ». Attendre / vérifier la fusion avant de baser une nouvelle feature sur upstream. |
| P0 | `1131fb66` est **plus récent** que le dernier SHA de nos échanges `0d022e736c` | Toute future opération locale commence par un **fetch + comparaison**, puis mise à jour prudente de la branche. Aucune réécriture/push maintenant. |
| P0 | **3 fils** sont encore ouverts dans l'interface malgré les réponses/corrections | Ne pas conclure « toutes les conversations résolues ». Aucune correction immédiate identifiée dans ces trois fils. |
| P1 | **8 jobs Actions verts** vérifiés au HEAD, mais statut du contrôle DCO indépendant non obtenu | Conserver la nuance ; vérification ciblée dans l'onglet Checks/commits si nécessaire, sans relancer les tests. |
| P1 | [#14114](https://github.com/organicmaps/organicmaps/issues/14114) reste « OPEN » alors que le correctif est intégré à #13533 | **Ne pas créer une seconde implémentation** ; contrôler sa clôture par la fusion de la PR (`Fixes #14114`). |
| P1 | [#14134](https://github.com/organicmaps/organicmaps/issues/14134) formalise le coût/placement du mapping Wear côté non-Google | Nouvelle tâche **distincte** ; pas un motif pour modifier la PR déjà approuvée. |
| P1 | Expérience montre écran inactif / retour au cadran non couverte par #13533 | **#14115** = prochaine fonctionnalité prioritaire et déjà choisie publiquement par le contributeur. |
| P2 | Les deux textes de statut Wear ne sont pas traduits dans toutes les langues | Suivre **#14116**, séparément de #13533 et de #14045 déjà fusionnée. |
| P2 | Release Wear non active, flèches/haptiques absentes | **#14117**, puis **#14118** selon leurs dépendances ; ne pas déclarer Wear OS « publié ». |
| P2 | État Git local inconnu, possibles changements annexes historiques | Demander/consulter `git status --short`, `git branch -vv` et `git log` **seulement avant la prochaine manipulation locale** ; ne pas extrapoler aujourd'hui. |

## 8. Prochaines actions sans relancer le travail terminé

**P0 — Stabilisation de #13533**

- Ne **rien** pousser, rebaser, commenter ni fermer sur GitHub à l'issue du présent audit sans validation explicite.
- Si la PR change : relire uniquement l'événement (nouveau SHA, review, check ou fusion) et actualiser ce document. Ne pas reprendre l'ensemble des tests par défaut.
- En cas de reprise de développement local sur cette branche : comparer `git fetch` avec le HEAD public `1131fb66` avant toute action susceptible d'écraser les retouches de `biodranik`.
- Lors de la fusion : enregistrer le SHA fusionné et vérifier la clôture de #14114 ainsi que le sort des fils de revue. Éviter de confondre la future fusion avec l'issue déjà corrigée.

**P1 — Développement suivant : [#14115](https://github.com/organicmaps/organicmaps/issues/14115)**

- Confirmer l'état upstream/issue au démarrage effectif du travail (sans prétexte à répéter les anciennes validations).
- Concevoir le **service watch** et l'**Ongoing Activity**, propriétaires de la capacité de réception pendant l'arrière-plan, en cohérence avec le contrat de #13533.
- Préparer des tests lifecycle ciblés : passage Activity→service, fermeture/réouverture, arrêt navigation, absence de fuite de listeners, remise à disposition des détails ; privilégier comportement mesurable et consommation maîtrisée.
- Créer une PR **indépendante** uniquement quand #13533 est fusionnée ou qu'un choix de base a été confirmé ; ne pas modifier #13533 pour réaliser ce suivi.

**P1/P2 — Refactorisation indépendante : [#14134](https://github.com/organicmaps/organicmaps/issues/14134)**

- Déplacer le mapping `RoutingInfo` vers `WearBridge` / `:sdk:wear:core` selon le contrat de l'issue, en gardant les invalidations dans le service ; éviter les allocations avec publisher no-op.
- Peut être préparée séparément de #14115 après examen de son coût ; ne pas retarder arbitrairement la PR déjà approuvée.

**P2/P3 — Compléments planifiés**

- **#14116** : messages de statut dans `data/strings/strings.txt`, régénération contrôlée et commit séparé, sans réintroduire `-r` dans le script.
- **#14117** : transmettre la direction de manœuvre et ajouter l'haptique ; dépendance fonctionnelle avec #14115 pour la montre inactive.
- **#14118** : activer les variantes release, publier le bundle Wear et sa piste Play, artefacts beta/signature ; examiner #13913 avant choix de versionnement.

## 9. Règles de tenue de cet état de projet

1. **Source de vérité** : GitHub pour PR/reviews/issues/CI ; `git status`, `git log`, `git diff` pour le local ; traces de build seulement pour les tests **effectivement** exécutés.
2. **Chaque changement de statut** : noter date, SHA, lien et impact, y compris si aucune action n'est requise.
3. **Réutiliser l'acquis** : ne pas proposer une correction déjà faite, une réponse déjà publiée, une PR déjà fusionnée ou un test déjà vert sur un HEAD inchangé.
4. **Différencier** « constat actuel », « ancienne preuve », « déclaration dans la PR » et « hypothèse ». Ne jamais convertir un historique de discussion en validation récente.
5. **Décision avant mutation** : aucun commentaire, rebase, push, clôture d'issue ni modification du dépôt upstream sans instruction explicite ; l'utilisateur valide les écarts et le document avant publication dans le fork/espace privé.
6. **Mise à jour ciblée** : lors d'une nouvelle session, vérifier en premier #13533 et les issues actives ; ne pas rejouer toute l'enquête sauf changement matériel de périmètre.
7. **Conserver la provenance** : associer aux décisions de nouvelles références GitHub et, pour les tests locaux, les commandes + résultats dans la prochaine révision de ce fichier.

---

### Liens primaires de contrôle rapide

- [PR #13533 (état, diff, discussions, checks)](https://github.com/organicmaps/organicmaps/pull/13533)
- [Commit HEAD vérifié du 07/11](https://github.com/organicmaps/organicmaps/commit/1131fb667b9974f2e43c34613a52e9823828a3fe)
- [Actions : Android Check sur le HEAD](https://github.com/organicmaps/organicmaps/actions/runs/42367555925)
- [Approval `strider-dunadan` — 06/11](https://github.com/organicmaps/organicmaps/pull/13533#pullrequestreview-5799655707)
- [Approval `biodranik` — 07/11](https://github.com/organicmaps/organicmaps/pull/13533#pullrequestreview-5811884536)
- [Correction script de traduction par mainteneur](https://github.com/organicmaps/organicmaps/pull/13533#discussion_r4501465399)
- [Suivi Wear #12030](https://github.com/organicmaps/organicmaps/issues/12030)
- [Ongoing Activity #14115](https://github.com/organicmaps/organicmaps/issues/14115)
- [Refactorisation non-Google #14134](https://github.com/organicmaps/organicmaps/issues/14134)

**Publication validée : `zefutoff/organicmaps`, branche isolée `project-tracking`, fichier racine `PROJECT_STATE.md`. Ne pas intégrer à `master`, à la branche de PR #13533 ni à upstream `organicmaps/organicmaps`.**