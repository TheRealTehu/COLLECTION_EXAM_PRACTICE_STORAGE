# **COLLECTIONS GYAKORLÓ FELADAT**
**Bevezetés**  
Egy élelmiszerraktár leprogramozására kértek fel. Sajnos a tulajdonos egy nagyon rossz rendszert talált ki és nagyon makacsul ragaszkodik hozzá, ezért a leírtak alapján kell megvalósítsd a feladatot. Tudnod kell tárolni a termékeket, kidobni a termékeket és különféle feltételek alapján listázni azokat.

**Feladatleírás**  
Készíts egy, a `Product` osztályt, amiben tárolni tudod a következő adatokat:  
a termék `nevét`, az `árát` egész számban tárolva, `lejárati dátumát` és a `kategóriákat`, amikbe tartozik. (String setben tárolva).  
Konstruktora kérje be az összes adatot.

Készíts el egy, `Storage` osztályt, mely:  
1. a termékek listáját konstruktor paraméterben kapja meg, ha ez null, dobjon `IllegalArgumentException`-t
2. A product listát getterrel el lehet érni
3. képes visszaadni az adott napon lejáró termékek listáját `List<Product> findProductsOnlyValidToday()`
4. képes visszaadni adott kategóriába tartozó termékek nevét `Set<String> getProductNamesInCategory(String category)`
5. képes visszaadni a lejárt termékek miatti veszteség összegét `calculateMoneylossOfExpiredProducts()`
6. képes visszaadni a kategóriák nevét csökkenő sorba rendezve az alapján, hogy melyik kategóriában mekkora az anyagi veszteség `List<String> listCategoriesOrderedByMoneylossDesc()`
7. képes kidobni minden lejárt terméket a raktárból `removeAllExpiredProducts()`

A munka során a kísérletezéshez tetszőlegesen létrehozhatsz main metódust, illetve akár készíthetsz másik osztályt main metódussal.  
A teszteket nem szabad módosítani!
