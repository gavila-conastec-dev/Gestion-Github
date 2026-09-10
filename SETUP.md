# Configuracion sin GitHub Actions

Estos archivos no ejecutan workflows y no consumen minutos de GitHub Actions.

## 1. Configurar la plantilla de commits

Cada persona debe ejecutar una vez en su clon local:

```bash
git config --local commit.template .gitmessage
```

La plantilla aparece al ejecutar:

```bash
git commit
```

No aparece cuando se usa `git commit -m`.

## 2. Configurar el equipo de QA

1. Crea o identifica el equipo `qa` dentro de la organizacion.
2. Concede al equipo acceso explicito al repositorio.
3. Sustituye `NOMBRE_ORGANIZACION` en:
   - `.github/CODEOWNERS`
   - `.github/pull_request_template.md`

Ejemplo:

```text
* @mi-organizacion/qa
```

## 3. Hacer obligatoria la aprobacion de QA

En la organizacion o repositorio, crea un ruleset para `main` y, si corresponde, `develop`:

- Require a pull request before merging.
- Require approvals.
- Require review from Code Owners.
- Dismiss stale pull request approvals when new commits are pushed.
- Require conversation resolution before merging.
- Block force pushes.
- Restrict deletions.

CODEOWNERS y los rulesets son funciones nativas y no consumen minutos de GitHub Actions.
La disponibilidad de algunas reglas puede depender del plan y del tipo de repositorio de la organizacion.

## 4. Convenciones recomendadas

```text
Rama:   feature/MIDAS-245-validar-comprobantes
Commit: feat(comprobantes): anadir validacion SUNAT
PR:     feat(comprobantes): validar documentos SUNAT
```

Al no incluir workflows, estas convenciones quedan documentadas, pero no se validan automaticamente.
La aprobacion de QA si puede exigirse mediante CODEOWNERS y el ruleset.

## Archivos incluidos

```text
.github/
├── CODEOWNERS
└── pull_request_template.md
.gitmessage
SETUP.md
```
