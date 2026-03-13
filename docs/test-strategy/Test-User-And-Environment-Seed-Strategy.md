# Test User and Environment Seed Strategy

## 1. Amaç

Bu doküman, Kanboard QA projesinde kullanılacak test kullanıcıları, temel seed veri yapısı, credential yönetimi ve environment reset yaklaşımını tanımlar.

Bu dokümanın amacı:

- testlerin her koşuda öngörülebilir bir başlangıç durumundan başlamasını sağlamak
- rol bazlı testleri aynı kullanıcı seti ile sürdürülebilir şekilde yürütmek
- manuel test, UI otomasyon ve API testleri için ortak bir baseline oluşturmak
- test verisi, cleanup ve reset yaklaşımını standart hale getirmek

Bu strateji, özellikle role-based access control, project management ve API doğrulama kapsamı için temel referans doküman olarak kullanılacaktır.

---

## 2. Temel Prensipler

Bu projede aşağıdaki prensipler esas alınır:

1. Test kullanıcıları rastgele veya manuel oluşturulmuş hesaplara dayanmaz.
2. Test ortamı her koşuda mümkün olduğunca aynı başlangıç durumuna döndürülür.
3. Dokümanlarda gerçek şifre tutulmaz; yalnızca kullanıcı alias ve rol bilgisi tutulur.
4. Gerçek username/password bilgileri source control dışında yönetilir.
5. Testler seed data ile çalışan sabit bir baseline üzerinden yürütülür.
6. Test tarafından üretilen geçici veriler baseline veri setinden ayrı değerlendirilir.
7. RBAC doğrulamalarında User API esas alınır.
8. Testler environment state’e bağımlı değil, tanımlı seed state’e bağımlı olacak şekilde tasarlanır.

---

## 3. Referans Rol Modeli

Kanboard’da yetkilendirme iki seviyelidir:

- **Application Roles**
    - Administrator
    - Manager
    - User

- **Project Roles**
    - Project Manager
    - Project Member
    - Project Viewer

Application seviyesinde `Manager` ile project seviyesindeki `Project Manager` aynı kavram değildir. Ayrıca local user oluşturma admin yetkisi gerektirir ve local user password policy minimum 6 karakterdir. Private/personal project’lerde permission management yoktur.

---

## 4. Test Kullanıcı Seti

Bu projede aşağıdaki alias seti standart kabul edilir. Bu set, BA dokümanındaki RBAC ortak ön koşul yapısı ile uyumludur.

| User Alias | Role Type | Assigned Role | Primary Usage |
|---|---|---|---|
| `U_ADMIN` | Application | Administrator | admin-only işlemler, user management, application scope doğrulamaları |
| `U_MANAGER` | Application | Manager | team project create, manager-level authorization testleri |
| `U_USER` | Application | User | personal project create, restricted behavior testleri |
| `U_PM` | Project | Project Manager | team project settings, permissions, remove/edit yetki testleri |
| `U_MEM` | Project | Project Member | task-level allowed/denied davranışları |
| `U_VIEW` | Project | Project Viewer | read-only erişim ve negatif authorization testleri |
| `U_CUST` | Project | Custom Project Role | restriction-based authorization testleri |

### 4.1 Kullanım Notu

`U_PM`, `U_MEM`, `U_VIEW` ve `U_CUST` hesapları application level’da genelde normal kullanıcı olarak tanımlanır; anlamlı hale gelmeleri için ilgili team project içine uygun project role ile atanmış olmaları gerekir.

### 4.2 Neden alias kullanılıyor?

Doküman, test case ve evidence içinde:

- gerçek username
- gerçek password
- gerçek token

yerine alias kullanılır.

Örnek:

- `User Alias: U_ADMIN`
- `User Alias: U_USER`
- `User Alias: U_PM`

Bu sayede doküman okunabilir kalır ve credential değişiklikleri test dokümanlarını bozmaz.

---

## 5. Baseline Proje Seti

Aşağıdaki minimum proje seti seed baseline içinde hazır bulunmalıdır.

| Project Alias | Project Type | Purpose |
|---|---|---|
| `TP1` | Team Project | team permission, role assignment, access behavior, project settings testleri |
| `PP1` | Personal Project | private/personal access behavior, owner-admin erişim testleri |

### 5.1 Ek kaynak projeler

Create project extended coverage ve copy davranışları için aşağıdaki kaynak projeler seed içinde ayrıca bulunabilir:

- `SRC_PROJECT_WITH_PERMISSIONS`
- `SRC_PROJECT_WITH_ACTIONS`
- `SRC_PROJECT_WITH_SWIMLANES`
- `SRC_PROJECT_WITH_CATEGORIES`
- `SRC_PROJECT_WITH_TASKS`

Bu projeler extended coverage aşamasında kullanılmalıdır; core regression için zorunlu değildir.

---

## 6. Credential Yönetim Stratejisi

## 6.1 Dokümanda ne tutulur?

Dokümanda yalnızca şu bilgiler tutulur:

- user alias
- role
- kullanım amacı

Örnek:

```text
User Alias: U_ADMIN
Role: Administrator
Purpose: Admin-level authorization and seed management
```

## 6.2 Koda ne girmez?

Aşağıdakiler doğrudan test class içine yazılmaz:

- gerçek username
- gerçek password
- personal access token
- API token

## 6.3 Gerçek credential nerede tutulur?

Gerçek credential’lar source control dışında tutulmalıdır.

Önerilen dosya:

```text
automation/src/test/resources/config/credentials.local.properties
```

Bu dosya `.gitignore` içinde olmalıdır.

Örnek yapı:

```properties
admin.username=u_admin
admin.password=Admin123!

manager.username=u_manager
manager.password=Manager123!

user.username=u_user
user.password=User123!

pm.username=u_pm
pm.password=Pm12345!

member.username=u_mem
member.password=Member123!

viewer.username=u_view
viewer.password=Viewer123!

custom.username=u_cust
custom.password=Custom123!
```

> Not: Şifre örnekleri temsilidir. Gerçek değerler local environment’a göre tanımlanmalıdır.

## 6.4 Test kodunda nasıl kullanılmalı?

Login tarafında tek generic giriş mekanizması tercih edilmelidir.

Önerilen kullanım mantığı:

- `loginAs(testUser)`
- `testUsers.admin()`
- `testUsers.manager()`
- `testUsers.standardUser()`

Ayrı ayrı hardcoded `loginAsAdmin`, `loginAsManager`, `loginAsUser` ile başlamak yerine generic user modeli daha sürdürülebilirdir.

---

## 7. API Kimlik Doğrulama Stratejisi

RBAC testlerinde **User API** kullanılmalıdır. Çünkü User API’de application roles ve project permissions her prosedürde kontrol edilir. Application API için ise permission check yapılmaz; bu nedenle RBAC doğrulaması için referans alınmamalıdır. Ayrıca denied durumunda 403 doğrulaması beklenir.

### 7.1 Kullanım ayrımı

| API Type | Usage |
|---|---|
| Application API | smoke, generic functional checks, admin-like unrestricted operasyonlar |
| User API | authorization, role validation, denied/allowed davranışları |

### 7.2 Kural

Aşağıdaki test tiplerinde mutlaka User API kullanılmalıdır:

- role-based create/update/remove testleri
- project permission testleri
- private/team access testleri
- custom role restriction testleri

---

## 8. Environment Reset Stratejisi

Mevcut `docker-compose.yml` yapısında aşağıdaki named volume’lar bulunmaktadır:

- `kanboard_data`
- `kanboard_plugins`
- `kanboard_db`

Bu nedenle environment state kalıcıdır. Sadece container restart yapmak temiz test başlangıcı sağlamaz.

Bu projede önerilen reset yaklaşımı:

## 8.1 Tercih edilen yaklaşım: Baseline restore

En doğru yöntem, seed hazırlanmış bir baseline veritabanı snapshot’ını restore etmektir.

### Akış

1. Temiz environment ayağa kaldırılır
2. Admin kullanıcı ile seed işlemleri yapılır
3. Test user seti oluşturulur
4. `TP1` ve `PP1` oluşturulur
5. Gerekli role assignment’lar yapılır
6. Gerekli kaynak projeler oluşturulur
7. Bu state baseline olarak saklanır
8. Her suite öncesi bu baseline geri yüklenir

### Avantajlar

- repeatable test başlangıcı
- aynı user ID / project ID düzeni
- testten kalan kirli verilerin otomatik temizlenmesi
- manuel cleanup ihtiyacının azalması
- debugging kolaylığı

## 8.2 Geçici local yaklaşım: Full reset + reseed

Henüz baseline dump yoksa, local geliştirme aşamasında aşağıdaki geçici yaklaşım kullanılabilir:

1. Docker environment tamamen silinir
2. Volume’lar kaldırılır
3. Environment yeniden ayağa kaldırılır
4. Seed kullanıcılar yeniden oluşturulur
5. Baseline projeler yeniden oluşturulur

Bu yaklaşım öğrenme aşaması için yeterlidir ama uzun vadede maliyetlidir.

---

## 9. Seed Kapsamı

Baseline seed içinde aşağıdaki veriler bulunmalıdır.

## 9.1 Zorunlu seed verileri

- `U_ADMIN`
- `U_MANAGER`
- `U_USER`
- `U_PM`
- `U_MEM`
- `U_VIEW`
- `U_CUST`
- `TP1`
- `PP1`

## 9.2 Team project role assignment örneği

`TP1` içinde önerilen örnek ilişki:

- `U_PM` → Project Manager
- `U_MEM` → Project Member
- `U_VIEW` → Project Viewer
- `U_CUST` → Custom Project Role

## 9.3 Personal project owner örneği

`PP1` için önerilen owner:

- `U_USER`

Bu yapı private project erişim testlerini netleştirir.

---

## 10. Test Tarafında Veri Sınıflandırması

Test verisi iki grupta ele alınmalıdır.

## 10.1 Baseline data

Bunlar sabit ve reset sonrası her zaman aynı olması beklenen verilerdir:

- seed kullanıcılar
- baseline projeler
- role assignment’lar
- copy source projeleri
- sabit test board yapıları

## 10.2 Test-generated data

Bunlar test koşumu sırasında oluşur:

- `QA_Create_*`
- `QA_Edit_*`
- `QA_Remove_*`
- geçici task ve subtask kayıtları
- geçici permission ve action konfigürasyonları

Kural:
- Baseline data korunur
- Test-generated data ya test sonunda temizlenir ya da suite sonrası baseline restore ile sıfırlanır

Bu projede önerilen ana yaklaşım:
**tek tek cleanup yerine suite-level baseline restore**

---

## 11. Test Lifecycle Standardı

## 11.1 Before Suite

- environment erişimi doğrulanır
- gerekli container’lar sağlıklı durumda mı kontrol edilir
- baseline restore uygulanır
- kritik seed kullanıcılar ve baseline projeler doğrulanır
- smoke sanity yapılır

## 11.2 Before Test Class

- ilgili test class için hedef kullanıcı mevcut mu kontrol edilir
- gerekli hedef proje veya kaynak proje hazır mı kontrol edilir
- test datası benzersiz isim üretimi için hazırlanır

## 11.3 After Test Class

- zorunluysa yalnız class bazlı geçici veri temizlenir
- evidence ve loglar toplanır

## 11.4 After Suite

- environment kirli kaldıysa baseline reset planlanır
- test-generated veri baseline dışında kalmışsa raporlanır
- sonraki koşu için temiz başlangıç teyit edilir

---

## 12. Otomasyon Tasarım Kararları

## 12.1 Login standardı

Testlerde generic user objesi ile login yapılmalıdır.

Önerilen tasarım:

- `TestUser`
- `TestUsers`
- `loginAs(TestUser user)`

## 12.2 Doküman ve kod ilişki standardı

Test case içinde:

- `User Alias`
- `User Role`
- `Target Project Alias`

görünür olmalıdır.

Kod tarafında:

- gerçek credential config’ten okunur
- test methodu alias/role mantığı ile trace edilebilir kalır

## 12.3 Cleanup standardı

Create/edit/remove gibi modüllerde postcondition tarafında şu mantık kullanılmalıdır:

- test-generated kayıt cleanup için not edilir
- asıl temizlik suite-level restore ile garanti edilir

Bu yaklaşım, create/edit/remove modüllerindeki postcondition mantığı ile de uyumludur.

---

## 13. Önerilen Dosya Yapısı

```text
automation/
└── src/
    └── test/
        └── resources/
            ├── config/
            │   ├── test-config.properties
            │   └── credentials.local.properties
            │
            ├── seed/
            │   ├── baseline-users.md
            │   ├── baseline-projects.md
            │   └── baseline-notes.md
            │
            └── testdata/
                ├── user-aliases.json
                └── project-aliases.json
```

### Dosya amaçları

- `test-config.properties`  
  base url, browser, headless gibi environment ayarları

- `credentials.local.properties`  
  gerçek username/password bilgileri, source control dışında

- `baseline-users.md`  
  alias → role → purpose dökümü

- `baseline-projects.md`  
  proje alias, type ve kullanım amacı

- `user-aliases.json`  
  kod tarafında alias bazlı data mapping

- `project-aliases.json`  
  sabit proje referansları

---

## 14. Riskler ve Dikkat Noktaları

### 14.1 Doküman–ürün farkı

Authorization beklentileri ürün davranışıyla doğrulanmadan kesin kabul edilmemelidir. Seed kullanıcılar hazır olsa bile gerçek yetki davranışı test ile netleştirilmelidir.

### 14.2 Private project access

Private/personal project’lerde permission management yoktur ve erişim owner/admin odaklıdır. Bu nedenle `PP1` gibi bir baseline private project ayrı tutulmalıdır.

### 14.3 Kalıcı volume etkisi

Docker volume’lar temizlenmeden yapılan koşular birbirini kirletebilir. Bu, özellikle create/edit/remove testlerinde false positive ve false negative üretir.

### 14.4 Credential sızıntısı

Gerçek credential’ların:
- markdown dokümanlara
- test case dosyalarına
- commit geçmişine

girmemesi gerekir.

---

## 15. Son Karar

Bu projede uygulanacak standart yaklaşım şudur:

- Test user seti sabit alias yapısı ile tanımlanır
- Gerçek credential’lar local secret config’te tutulur
- Seed baseline kullanıcılar ve projeler hazırlanır
- Testler bu baseline üstünde çalışır
- RBAC doğrulamalarında User API kullanılır
- Environment reset için tercih edilen yöntem baseline restore’dur
- Tek tek manuel cleanup yerine suite-level reset ana strateji olarak benimsenir

Bu standart oturduğunda:
- test case yazımı sadeleşir
- automation tarafı daha traceable olur
- environment kaynaklı flaky davranışlar azalır
- Jenkins/CI geçişi çok daha kolay hale gelir
