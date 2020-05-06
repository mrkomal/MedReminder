package com.example.medreminder

//Nazwa pliku powinna odpowiadac nazwie interfejsu
interface SelectFragment {
    fun onFragmentSelected(fragmentID: FragmentID)
}

//Enum jest tak blisko zwiazany z tym interfejsem, ze nie ma sensu przenosic go do osobnego pliku
//Zawsze grupujemy stale, enumy, interfejsy na podstawie ich semantyki - do czego sie odnosza

//Dobrze jest wszelkiego rodzaju zmienne do porownan trzymac jako enumy,
// dzieki temu widac deskryptywna nazwe i nie trzeba sie domyslac ani komentowac kodu
enum class FragmentID(val id: Int){
    AddMedication(1),
    MyMedications(2),
    MyKit(3),
    More(4)
}

