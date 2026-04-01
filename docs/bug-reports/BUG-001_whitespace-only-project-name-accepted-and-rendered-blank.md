# BUG-001

- **Bug ID:** BUG-001
- **Title:** Whitespace-only project name is accepted and rendered as blank after create
- **Module:** Project Management / Create Project
- **Severity:** High
- **Priority:** High
- **Layer:** UI
- **Detection Method:** Automation
- **Detected By:** `TC_PM_CP_008_CreateProjectWhitespaceConsistencyTest`
- **Environment:** Local Kanboard QA environment

## Summary
Sistem yalnızca whitespace içeren project name girdilerini create sırasında kabul ediyor. Ancak create sonrası proje adı Summary ekranında ve My Projects listesinde boş/anlamsız şekilde görüntüleniyor. Edit ekranındaki input değeri ise whitespace’i koruyor. Bu durum kullanıcı açısından geçerli görünen fakat anlamlı şekilde görüntülenemeyen bir kayıt oluşturuyor.

## Preconditions
- Team project create yetkisine sahip bir kullanıcı ile giriş yapılmış olmalı.
- Create ekranı erişilebilir olmalı.

## Steps to Reproduce
1. New Project modalını aç.
2. Project Name alanına aşağıdaki değerlerden birini gir:
    - `" "`
    - `"     "`
3. Save ile create işlemini tamamla.
4. Summary ekranındaki proje adını kontrol et.
5. Edit project ekranındaki input değerini kontrol et.
6. My Projects listesindeki proje adını kontrol et.

## Actual Result
- Create işlemi başarılı şekilde ilerliyor.
- Summary ekranındaki proje adı boş görünüyor: `[]`
- My Projects listesindeki proje adı boş görünüyor: `[]`
- Edit project input değeri whitespace’i koruyor:
    - `[ ]`
    - `[     ]`

## Expected Result
Aşağıdaki davranışlardan biri beklenir:
- Sistem whitespace-only input’u create aşamasında engellemelidir,
  **veya**
- create kabul ediliyorsa proje adı kullanıcı açısından boş, anlamsız veya ayırt edilemez şekilde görüntülenmemelidir.

## Impact
- Kullanıcı görünürde oluşturulmuş ama adı boş olan proje ile karşılaşabilir.
- Liste ve detail ekranında anlamlı proje adı sunulamadığı için kayıt ayırt edilemez hale gelir.
- Uygulama veri kalitesi ve kullanılabilirlik açısından risk oluşturur.

## Reproducibility
- Tekrarlanabilir.
- `single-space-only` ve `multi-space-only` case’lerinde tekrarlandı.

## Related Test Artifact
- `TC_PM_CP_008_CreateProjectWhitespaceConsistencyTest`

## Notes
- Aynı testte trim-sensitive değerler için diğer case’ler geçti.
- Problem özellikle whitespace-only input’larda gözlendi.