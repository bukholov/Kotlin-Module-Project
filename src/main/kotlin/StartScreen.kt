class StartScreen {
    var archiveList: ArrayList<ArrayList<Note>> = ArrayList()

    fun startScreen(){
        printStartMenu()
        when(getDigitFromInput()){
            0-> createArchive()
            1-> selectArchive()
            2-> return
            else -> println("Такого пункта меню нет.")
        }
        startScreen()
    }

    fun printStartMenu(){
        println("-------------------------------------")
        println("0. Создать архив")
        println("1. Это мой уже созданный архив")
        println("2. Выход")
    }

    fun createArchive(){
        archiveList.add(ArrayList<Note>())
        println("Архив c №${archiveList.size} успешно создан!")
    }

    fun selectArchive(){
        printArchiveList()
        if(archiveList.isEmpty()){
            startScreen()
            return
        }
        println("Введите номер архива.")
        var input = getDigitFromInput()-1
        if(input in archiveList.indices)
            openArchiveScreen(input)
        else {
            println("Такого номера архива нет")
            selectArchive()
        }
    }

    fun printArchiveList(){
        println("-------------------------------------")
        if(archiveList.isEmpty())
            println("Список архивов пуст. Добавьте их для начала работы")
        else{
            println("Список архивов:")
            archiveList.forEachIndexed { index, notes ->  println("${index+1}. Заметки: "+if(notes.isEmpty())"отсутсвуют" else notes.map { it.name }.joinToString(","))}
        }
    }

    fun openArchiveScreen(archiveIndex: Int){
        printNoteListMenu()
        when(getDigitFromInput()){
            0-> createNote(archiveIndex)
            1-> selectNote(archiveIndex)
            2-> return
            else -> println("Такого пункта меню нет.")
        }
        openArchiveScreen(archiveIndex)
    }

    fun printNoteListMenu(){
        println("-------------------------------------")
        println("0. Создать заметку")
        println("1. Выбрать заметку")
        println("2. Выход")
    }

    fun createNote(archiveIndex: Int){
        println("Введите название заметки")
        val nameNote = getTextFromInput()
        println("Введите текст заметки")
        val messageNote = getTextFromInput()

        archiveList[archiveIndex].add(Note(nameNote, messageNote))
    }

    fun getDigitFromInput():Int{
        var inputDigit = readLine()?.toIntOrNull()
        while (inputDigit == null){
            inputDigit = readLine()?.toIntOrNull()
            println("Ввод должен содержать цифру")
        }
        return inputDigit
    }

    fun getTextFromInput():String{
        var inputText = readLine()
        while (inputText.isNullOrBlank()){
            inputText = readLine()
            println("Ввод не должен быть пустым")
        }
        return inputText
    }

    fun selectNote(archiveIndex: Int){
        printNoteList(archiveIndex)
        if(archiveList[archiveIndex].isEmpty())
            return

        println("Выберите номер заметки.")
        var input = getDigitFromInput()-1

        if(input in archiveList[archiveIndex].indices)
            openNote(archiveList[archiveIndex][input])
        else {
            println("Такого номера заметки нет.")
            selectNote(archiveIndex)
        }
    }

    fun printNoteList(archiveIndex: Int){
        println("-------------------------------------")
        if(archiveList[archiveIndex].isEmpty())
            println("Список заметок пуст!")
        else{
            println("Список заметок: ")
            archiveList[archiveIndex].map { it.name }.forEachIndexed { index, s ->  println("${index+1}. ${s}") }
        }
    }

    fun openNote(note: Note){
        println("-------------------------------------")
        println("Заметка \"${note.name}\"\n${note.message}")
        return
    }
}