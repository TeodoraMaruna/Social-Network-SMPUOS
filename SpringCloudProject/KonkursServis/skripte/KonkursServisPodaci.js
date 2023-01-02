var db = connect('localhost:27017/BazaPodatakaKonkursa');

db.prijavljeniKandidati.remove({});
db.kandidati.remove({});
db.studijskiProgramiNaKonkursima.remove({});
db.konkursi.remove({});

db.konkursi.insert(
[{
	"_id": "А1",
	"naziv": "Конкурс рачунарских наука и софтверског инжењеринга",
	"opis": "Конкурс за мастер академске студије из рачунарских наука и софтверског инжењеринга.",
	"skolskaGodina": "2020/2021",
	"redniBroj": 1,
	"datumPocetka": ISODate("2020-09-01"),
	"datumZavrsetka": ISODate("2020-10-01")
},
{
	"_id": "А2",
	"naziv": "Конкурс рачунарских наука и софтверског инжењеринга",
	"opis": "Конкурс за мастер академске студије из рачунарских наука и софтверског инжењеринга.",
	"skolskaGodina": "2021/2022",
	"redniBroj": 1,
	"datumPocetka": ISODate("2021-09-01"),
	"datumZavrsetka": ISODate("2021-10-01")
},
{
	"_id": "А3",
	"naziv": "Конкурс информационих технологија",
	"opis": "Конкурс за мастер академске студије из информационих технологија.",
	"skolskaGodina": "2021/2022",
	"redniBroj": 2,
	"datumPocetka": ISODate("2021-09-05"),
	"datumZavrsetka": ISODate("2021-10-05")
}]
);

db.studijskiProgramiNaKonkursima.insert(
[{
	"_id": "А1СП1",
	"konkursId": "А1",
	"studijskiProgramId": "СП1",
	"jezikStudija": "Српски",
	"brojBudzetskihMesta": 40,
	"brojSamofinansirajucihMesta": 10,
	"skolarinaZaDomaceStudente": 1000,
	"skolarinaZaStraneStudente": 1500
},
{
	"_id": "А1СП3",
	"konkursId": "А1",
	"studijskiProgramId": "СП3",
	"jezikStudija": "Српски",
	"brojBudzetskihMesta": 25,
	"brojSamofinansirajucihMesta": 5,
	"skolarinaZaDomaceStudente": 1000,
	"skolarinaZaStraneStudente": 1500
},
{
	"_id": "А2СП1",
	"konkursId": "А2",
	"studijskiProgramId": "СП1",
	"jezikStudija": "Српски",
	"brojBudzetskihMesta": 45,
	"brojSamofinansirajucihMesta": 5,
	"skolarinaZaDomaceStudente": 1100,
	"skolarinaZaStraneStudente": 1600
},
{
	"_id": "А2СП3",
	"konkursId": "А2",
	"studijskiProgramId": "СП3",
	"jezikStudija": "Српски",
	"brojBudzetskihMesta": 28,
	"brojSamofinansirajucihMesta": 2,
	"skolarinaZaDomaceStudente": 1100,
	"skolarinaZaStraneStudente": 1600
},
{
	"_id": "А3СП2",
	"konkursId": "А3",
	"studijskiProgramId": "СП2",
	"jezikStudija": "Енглески",
	"brojBudzetskihMesta": 50,
	"brojSamofinansirajucihMesta": 10,
	"skolarinaZaDomaceStudente": 1300,
	"skolarinaZaStraneStudente": 1800
}]
);

db.kandidati.insert(
[{
	"_id": "ЦН1",
	"ime": "Јована",
	"prezime": "Јовановић",
	"datumRodjenja": ISODate("2001-07-10"),
	"adresa": "Булевар ослобођења 33, Нови Сад",
	"poeniIzSrednjeSkole": 36.1
},
{
	"_id": "ЦН2",
	"ime": "Петар",
	"prezime": "Петровић",
	"datumRodjenja": ISODate("2001-02-15"),
	"adresa": "Новосадрског сајма 20, Нови Сад",
	"poeniIzSrednjeSkole": 31.7
},
{
	"_id": "ЦН3",
	"ime": "Ивана",
	"prezime": "Ивановић",
	"datumRodjenja": ISODate("2001-05-30"),
	"adresa": "Николе Тесле 15, Футог",
	"poeniIzSrednjeSkole": 40
},
{
	"_id": "ЦН4",
	"ime": "Жарко",
	"prezime": "Жарковић",
	"datumRodjenja": ISODate("2001-01-21"),
	"adresa": "Шумадијска 2, Каћ",
	"poeniIzSrednjeSkole": 28.3
},
{
	"_id": "ЦН5",
	"ime": "Бојана",
	"prezime": "Бојанић",
	"datumRodjenja": ISODate("2002-03-26"),
	"adresa": "Змај Јовина 5, Темерин",
	"poeniIzSrednjeSkole": 38.8
},
{
	"_id": "ЦН6",
	"ime": "Никола",
	"prezime": "Николић",
	"datumRodjenja": ISODate("2002-11-09"),
	"adresa": "Владислава Петковића Диса 14, Петроварадин",
	"poeniIzSrednjeSkole": 35.5
},
{
	"_id": "ЦН7",
	"ime": "Анђела",
	"prezime": "Анђелић",
	"datumRodjenja": ISODate("2002-06-27"),
	"adresa": "Вука Караџића 30, Ветерник",
	"poeniIzSrednjeSkole": 30.9
},
{
	"_id": "ЦН8",
	"ime": "Марко",
	"prezime": "Марковић",
	"datumRodjenja": ISODate("2002-12-15"),
	"adresa": "Иве Андрића 25, Сремски Карловци",
	"poeniIzSrednjeSkole": 40
}]
);

db.prijavljeniKandidati.insert(
[{
	"_id": "А1СП1ЦН1",
	"studijskiProgramNaKonkursuId": "А1СП1",
	"kandidatId": "ЦН1",
	"datumPrijave": ISODate("2020-09-06"),
	"poeniSaPrijemnogIspita": 50.1
},
{
	"_id": "А1СП1ЦН2",
	"studijskiProgramNaKonkursuId": "А1СП1",
	"kandidatId": "ЦН2",
	"datumPrijave": ISODate("2020-09-10"),
	"poeniSaPrijemnogIspita": 44.5
},
{
	"_id": "А1СП1ЦН3",
	"studijskiProgramNaKonkursuId": "А1СП1",
	"kandidatId": "ЦН3",
	"datumPrijave": ISODate("2020-09-02"),
	"poeniSaPrijemnogIspita": 55
},
{
	"_id": "А1СП3ЦН1",
	"studijskiProgramNaKonkursuId": "А1СП3",
	"kandidatId": "ЦН1",
	"datumPrijave": ISODate("2020-09-14"),
	"poeniSaPrijemnogIspita": 52.7
},
{
	"_id": "А1СП3ЦН4",
	"studijskiProgramNaKonkursuId": "А1СП3",
	"kandidatId": "ЦН4",
	"datumPrijave": ISODate("2020-09-12"),
	"poeniSaPrijemnogIspita": 22.3
},
{
	"_id": "А2СП1ЦН4",
	"studijskiProgramNaKonkursuId": "А2СП1",
	"kandidatId": "ЦН4",
	"datumPrijave": ISODate("2021-09-25"),
	"poeniSaPrijemnogIspita": 49.2
},
{
	"_id": "А2СП1ЦН5",
	"studijskiProgramNaKonkursuId": "А2СП1",
	"kandidatId": "ЦН5",
	"datumPrijave": ISODate("2021-09-13"),
	"poeniSaPrijemnogIspita": 54
},
{
	"_id": "А2СП1ЦН6",
	"studijskiProgramNaKonkursuId": "А2СП1",
	"kandidatId": "ЦН6",
	"datumPrijave": ISODate("2021-09-06"),
	"poeniSaPrijemnogIspita": 56.2
},
{
	"_id": "А2СП3ЦН4",
	"studijskiProgramNaKonkursuId": "А2СП3",
	"kandidatId": "ЦН4",
	"datumPrijave": ISODate("2021-09-25"),
	"poeniSaPrijemnogIspita": 49.2
},
{
	"_id": "А2СП3ЦН6",
	"studijskiProgramNaKonkursuId": "А2СП3",
	"kandidatId": "ЦН6",
	"datumPrijave": ISODate("2021-09-06"),
	"poeniSaPrijemnogIspita": 56.2
},
{
	"_id": "А3СП2ЦН4",
	"studijskiProgramNaKonkursuId": "А3СП2",
	"kandidatId": "ЦН4",
	"datumPrijave": ISODate("2021-09-30"),
	"poeniSaPrijemnogIspita": 51.3
},
{
	"_id": "А3СП2ЦН5",
	"studijskiProgramNaKonkursuId": "А3СП2",
	"kandidatId": "ЦН5",
	"datumPrijave": ISODate("2021-09-19"),
	"poeniSaPrijemnogIspita": 54.1
},
{
	"_id": "А3СП2ЦН7",
	"studijskiProgramNaKonkursuId": "А3СП2",
	"kandidatId": "ЦН7",
	"datumPrijave": ISODate("2021-09-07"),
	"poeniSaPrijemnogIspita": 48
},
{
	"_id": "А3СП2ЦН8",
	"studijskiProgramNaKonkursuId": "А3СП2",
	"kandidatId": "ЦН8",
	"datumPrijave": ISODate("2021-09-05"),
	"poeniSaPrijemnogIspita": 60
}]
);