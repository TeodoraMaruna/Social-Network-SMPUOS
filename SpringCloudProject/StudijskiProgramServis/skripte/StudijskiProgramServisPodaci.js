var db = connect('localhost:27017/BazaPodatakaStudijskihPrograma');

db.pozicijePredmetaNaStudijskimProgramima.remove({});
db.pozicije.remove({});
db.predmeti.remove({});
db.studijskiProgrami.remove({});

db.studijskiProgrami.insert(
[{
	"_id": "СП1",
	"naziv": "Рачунарске науке",
	"nivoStudija": "Мастер академске студије",
	"zvanjeKojeSeStice": "Мастер инжењер рачунарства",
	"obrazovnoPolje": "Техничко-технолошке науке",
	"brojSemestara": 2,
	"ukupanBrojESPB": 64,
	"ocekivaniBrojKandidata": 50
},
{
	"_id": "СП2",
	"naziv": "Информационе технологије",
	"nivoStudija": "Мастер академске студије",
	"zvanjeKojeSeStice": "Мастер информационих технологија",
	"obrazovnoPolje": "Интердисциплинарно",
	"brojSemestara": 2,
	"ukupanBrojESPB": 62,
	"ocekivaniBrojKandidata": 60
},
{
	"_id": "СП3",
	"naziv": "Софтверски инжењеринг",
	"nivoStudija": "Мастер академске студије",
	"zvanjeKojeSeStice": "Мастер софтвер инжењер",
	"obrazovnoPolje": "Техничко-технолошке науке",
	"brojSemestara": 2,
	"ukupanBrojESPB": 65,
	"ocekivaniBrojKandidata": 30
}]
);

db.predmeti.insert(
[{
	"_id": "Ц1",
	"naziv": "Информациони системи",
	"sifra": "ИС-573",
	"opis": "Напредне технике имплементације информационих система.",
	"fondPredavanja": 4,
	"fondAuditornihVezbi": 2,
	"fondDrugihOblikaNastave": 2,
	"espb": 9
},
{
	"_id": "Ц2",
	"naziv": "Напредно програмирање",
	"sifra": "НП-741",
	"opis": "Примена напредних метода програмирања у различитим доменима.",
	"fondPredavanja": 3,
	"fondAuditornihVezbi": 3,
	"fondDrugihOblikaNastave": 0,
	"espb": 8
},
{
	"_id": "Ц3",
	"naziv": "Наменско моделовање",
	"sifra": "НМ-134",
	"opis": "Имплементација наменских језика и моделовање.",
	"fondPredavanja": 4,
	"fondAuditornihVezbi": 4,
	"fondDrugihOblikaNastave": 0,
	"espb": 9
},
{
	"_id": "Ц4",
	"naziv": "Агилни развој алата",
	"sifra": "АР-986",
	"opis": "Примена методологије агилног развоја алата.",
	"fondPredavanja": 4,
	"fondAuditornihVezbi": 4,
	"fondDrugihOblikaNastave": 0,
	"espb": 9
},
{
	"_id": "Ц5",
	"naziv": "Моделовање процеса",
	"sifra": "МП-324",
	"opis": "Моделовање пословних и производних процеса.",
	"fondPredavanja": 4,
	"fondAuditornihVezbi": 4,
	"fondDrugihOblikaNastave": 0,
	"espb": 9
},
{
	"_id": "Ц6",
	"naziv": "Технике анализа података",
	"sifra": "АП-451",
	"opis": "Употреба напредних техника анализа података.",
	"fondPredavanja": 3,
	"fondAuditornihVezbi": 0,
	"fondDrugihOblikaNastave": 3,
	"espb": 7
},
{
	"_id": "Ц7",
	"naziv": "Технике складиштења података",
	"sifra": "СП-548",
	"opis": "Примена техника складиштења података у дистрибуираним системима.",
	"fondPredavanja": 3,
	"fondAuditornihVezbi": 2,
	"fondDrugihOblikaNastave": 1,
	"espb": 7
},
{
	"_id": "Ц8",
	"naziv": "Рачунарска визуелизација",
	"sifra": "РВ-694",
	"opis": "Рад са ЦАД/ЦАМ техникама моделовања.",
	"fondPredavanja": 3,
	"fondAuditornihVezbi": 0,
	"fondDrugihOblikaNastave": 3,
	"espb": 8
},
{
	"_id": "Ц9",
	"naziv": "Системи високих перформанси",
	"sifra": "ВП-342",
	"opis": "Имплементација система високих перформанси.",
	"fondPredavanja": 4,
	"fondAuditornihVezbi": 2,
	"fondDrugihOblikaNastave": 2,
	"espb": 9
},
{
	"_id": "Ц10",
	"naziv": "Мастер рад",
	"sifra": "МР-100",
	"opis": "Теоријске основе и практична примена из области рачунарства.",
	"fondPredavanja": 0,
	"fondAuditornihVezbi": 0,
	"fondDrugihOblikaNastave": 0,
	"espb": 14
}]
);

db.pozicije.insert(
[{
	"_id": "СП1П1",
	"studijskiProgramId": "СП1",
	"redniBroj": 1,
	"semestar": 1,
	"status": "Обавезан"
},
{
	"_id": "СП1П2",
	"studijskiProgramId": "СП1",
	"redniBroj": 2,
	"semestar": 1,
	"status": "Обавезан"
},
{
	"_id": "СП1П3",
	"studijskiProgramId": "СП1",
	"redniBroj": 3,
	"semestar": 1,
	"status": "Изборни"
},
{
	"_id": "СП1П4",
	"studijskiProgramId": "СП1",
	"redniBroj": 4,
	"semestar": 1,
	"status": "Изборни"
},
{
	"_id": "СП1П5",
	"studijskiProgramId": "СП1",
	"redniBroj": 5,
	"semestar": 1,
	"status": "Обавезан"
},
{
	"_id": "СП1П6",
	"studijskiProgramId": "СП1",
	"redniBroj": 6,
	"semestar": 2,
	"status": "Обавезан"
},
{
	"_id": "СП1П7",
	"studijskiProgramId": "СП1",
	"redniBroj": 7,
	"semestar": 2,
	"status": "Обавезан"
},
{
	"_id": "СП2П1",
	"studijskiProgramId": "СП2",
	"redniBroj": 1,
	"semestar": 1,
	"status": "Обавезан"
},
{
	"_id": "СП2П2",
	"studijskiProgramId": "СП2",
	"redniBroj": 2,
	"semestar": 1,
	"status": "Обавезан"
},
{
	"_id": "СП2П3",
	"studijskiProgramId": "СП2",
	"redniBroj": 3,
	"semestar": 1,
	"status": "Обавезан"
},
{
	"_id": "СП2П4",
	"studijskiProgramId": "СП2",
	"redniBroj": 4,
	"semestar": 1,
	"status": "Обавезан"
},
{
	"_id": "СП2П5",
	"studijskiProgramId": "СП2",
	"redniBroj": 5,
	"semestar": 1,
	"status": "Обавезан"
},
{
	"_id": "СП2П6",
	"studijskiProgramId": "СП2",
	"redniBroj": 6,
	"semestar": 2,
	"status": "Изборни"
},
{
	"_id": "СП2П7",
	"studijskiProgramId": "СП2",
	"redniBroj": 7,
	"semestar": 2,
	"status": "Обавезан"
},
{
	"_id": "СП3П1",
	"studijskiProgramId": "СП3",
	"redniBroj": 1,
	"semestar": 1,
	"status": "Обавезан"
},
{
	"_id": "СП3П2",
	"studijskiProgramId": "СП3",
	"redniBroj": 2,
	"semestar": 1,
	"status": "Обавезан"
},
{
	"_id": "СП3П3",
	"studijskiProgramId": "СП3",
	"redniBroj": 3,
	"semestar": 1,
	"status": "Обавезан"
},
{
	"_id": "СП3П4",
	"studijskiProgramId": "СП3",
	"redniBroj": 4,
	"semestar": 1,
	"status": "Обавезан"
},
{
	"_id": "СП3П5",
	"studijskiProgramId": "СП3",
	"redniBroj": 5,
	"semestar": 1,
	"status": "Изборни"
},
{
	"_id": "СП3П6",
	"studijskiProgramId": "СП3",
	"redniBroj": 6,
	"semestar": 2,
	"status": "Изборни"
},
{
	"_id": "СП3П7",
	"studijskiProgramId": "СП3",
	"redniBroj": 7,
	"semestar": 2,
	"status": "Обавезан"
}]
);

db.pozicijePredmetaNaStudijskimProgramima.insert(
[{
	"_id": "СП1П1Ц1",
	"studijskiProgramId": "СП1",
	"pozicijaId": "СП1П1",
	"predmetId": "Ц1"
},
{
	"_id": "СП1П2Ц2",
	"studijskiProgramId": "СП1",
	"pozicijaId": "СП1П2",
	"predmetId": "Ц2"
},
{
	"_id": "СП1П3Ц3",
	"studijskiProgramId": "СП1",
	"pozicijaId": "СП1П3",
	"predmetId": "Ц3"
},
{
	"_id": "СП1П3Ц4",
	"studijskiProgramId": "СП1",
	"pozicijaId": "СП1П3",
	"predmetId": "Ц4"
},
{
	"_id": "СП1П3Ц5",
	"studijskiProgramId": "СП1",
	"pozicijaId": "СП1П3",
	"predmetId": "Ц5"
},
{
	"_id": "СП1П4Ц6",
	"studijskiProgramId": "СП1",
	"pozicijaId": "СП1П4",
	"predmetId": "Ц6"
},
{
	"_id": "СП1П4Ц7",
	"studijskiProgramId": "СП1",
	"pozicijaId": "СП1П4",
	"predmetId": "Ц7"
},
{
	"_id": "СП5П1Ц8",
	"studijskiProgramId": "СП1",
	"pozicijaId": "СП1П5",
	"predmetId": "Ц8"
},
{
	"_id": "СП6П1Ц9",
	"studijskiProgramId": "СП1",
	"pozicijaId": "СП1П6",
	"predmetId": "Ц9"
},
{
	"_id": "СП7П1Ц10",
	"studijskiProgramId": "СП1",
	"pozicijaId": "СП1П7",
	"predmetId": "Ц10"
},
{
	"_id": "СП2П1Ц1",
	"studijskiProgramId": "СП2",
	"pozicijaId": "СП2П1",
	"predmetId": "Ц1"
},
{
	"_id": "СП2П2Ц2",
	"studijskiProgramId": "СП2",
	"pozicijaId": "СП2П2",
	"predmetId": "Ц2"
},
{
	"_id": "СП2П3Ц6",
	"studijskiProgramId": "СП2",
	"pozicijaId": "СП2П3",
	"predmetId": "Ц6"
},
{
	"_id": "СП2П4Ц7",
	"studijskiProgramId": "СП2",
	"pozicijaId": "СП2П4",
	"predmetId": "Ц7"
},
{
	"_id": "СП2П5Ц8",
	"studijskiProgramId": "СП2",
	"pozicijaId": "СП2П5",
	"predmetId": "Ц8"
},
{
	"_id": "СП2П6Ц5",
	"studijskiProgramId": "СП2",
	"pozicijaId": "СП2П6",
	"predmetId": "Ц5"
},
{
	"_id": "СП2П6Ц9",
	"studijskiProgramId": "СП2",
	"pozicijaId": "СП2П6",
	"predmetId": "Ц9"
},
{
	"_id": "СП2П7Ц10",
	"studijskiProgramId": "СП2",
	"pozicijaId": "СП2П7",
	"predmetId": "Ц10"
},
{
	"_id": "СП3П1Ц2",
	"studijskiProgramId": "СП3",
	"pozicijaId": "СП3П1",
	"predmetId": "Ц2"
},
{
	"_id": "СП3П2Ц3",
	"studijskiProgramId": "СП3",
	"pozicijaId": "СП3П2",
	"predmetId": "Ц3"
},
{
	"_id": "СП3П3Ц4",
	"studijskiProgramId": "СП3",
	"pozicijaId": "СП3П3",
	"predmetId": "Ц4"
},
{
	"_id": "СП3П4Ц5",
	"studijskiProgramId": "СП3",
	"pozicijaId": "СП3П4",
	"predmetId": "Ц5"
},
{
	"_id": "СП3П5Ц6",
	"studijskiProgramId": "СП3",
	"pozicijaId": "СП3П5",
	"predmetId": "Ц6"
},
{
	"_id": "СП3П5Ц7",
	"studijskiProgramId": "СП3",
	"pozicijaId": "СП3П5",
	"predmetId": "Ц7"
},
{
	"_id": "СП3П6Ц1",
	"studijskiProgramId": "СП3",
	"pozicijaId": "СП3П6",
	"predmetId": "Ц1"
},
{
	"_id": "СП3П6Ц9",
	"studijskiProgramId": "СП3",
	"pozicijaId": "СП3П6",
	"predmetId": "Ц9"
},
{
	"_id": "СП3П7Ц10",
	"studijskiProgramId": "СП3",
	"pozicijaId": "СП3П7",
	"predmetId": "Ц10"
}]
);