# DevJoint AuthFlowApp

Bu layihə Android platforması üçün **Kotlin** və **Jetpack Compose** istifadə edərək hazırlanmış müasir avtorizasiya (Authentication Flow) tətbiqidir. Layihə çərçivəsində təmiz arxitektura, qabaqcıl state idarəetməsi və cihaz funksiyalarının inteqrasiyası həyata keçirilmişdir.

## 🚀 Texnologiyalar və Kitabxanalar
* **Dil:** Kotlin
* **UI Framework:** Jetpack Compose (Material 3)
* **Arxitektura:** MVVM (Model-View-ViewModel)
* **Asinxron Əməliyyatlar:** Kotlin Coroutines & StateFlow
* **Naviqasiya:** Jetpack Navigation Compose
* **Yaddaş:** SharedPreferences (Sessiya idarəetməsi üçün)
* **Cihaz Xüsusiyyəti:** CameraX / ActivityResultContracts (Kamera icazəsi və şəkil çəkmə)

## ✨ Əsas Xüsusiyyətlər
1. **Login & Register UI & Validasiya:** E-poçt formatının Regex ilə yoxlanılması, şifrə uzunluğu və şifrələrin uyğunluğunun təsdiqi.
2. **Mock JWT Autentifikasiya:** Serverə ehtiyac duyulmadan ViewModel daxilində simulyasiya edilmiş təhlükəsiz giriş sistemi.
3. **Sessiya Qorunması (Route Protection):** Tətbiq yenidən açıldıqda istifadəçinin daxil olub-olmadığını yoxlayan və müvafiq ekrana yönləndirən mexanizm.
4. **Qabaqcıl State İdarəetməsi:** `AuthState` sealed class və `StateFlow` vasitəsilə UI vəziyyətlərinin (`Loading`, `Success`, `Error`) idarə olunması.
5. **Cihaz İnteqrasiyası:** Runtime (işləmə zamanı) kamera icazə idarəetməsi ilə profil şəklinin çəkilməsi.
6. **Xəta İdarəetməsi və Logout:** Yanlış məlumatlar və ya şəbəkə xətaları zamanı xəbərdarlıq mesajları və təhlükəsiz çıxış funksiyası.