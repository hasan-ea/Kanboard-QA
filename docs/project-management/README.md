# Project Management Module

Bu klasör, **Kanboard QA projesi** kapsamında hazırlanmış **Project Management** modülüne ait test analiz ve test tasarım dokümanlarını içerir.

## Amaç

Bu modülün amacı, proje yaşam döngüsündeki temel yönetim fonksiyonlarını QA bakış açısıyla kapsamak ve aşağıdaki alanlar için düzenli, izlenebilir ve portfolyo seviyesinde profesyonel bir test dokümantasyonu oluşturmaktır:

- Create Project
- Edit Project
- Project Type / Access Behavior
- Project Views
- Default Settings
- Remove Project
- Authorization / Negative Cases

## Doküman Yapısı

Her alt başlık mümkün olduğunca aynı standardı takip eder:

- **Test Scenarios**
  - İlgili alan için kapsamı ve senaryo envanterini tanımlar
- **Core Regression Test Cases**
  - En kritik akışların detaylı test case’lerini içerir
- **Extended Coverage Test Cases**
  - Ek kapsam, varyasyon ve ikinci seviye senaryoları içerir

Bu yapı sayesinde:
- önce neyin test edileceği netleştirilir
- sonra kritik senaryolar test case seviyesine indirilir
- ardından kapsam kontrollü şekilde genişletilir

## Klasör Yapısı

```text
project-management/
├── create-project/
├── edit-project/
├── project-type-access-behavior/
├── project-views/
├── default-settings/
├── remove-project/
└── authorization-negative-cases/