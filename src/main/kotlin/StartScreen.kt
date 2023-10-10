class StartScreen {
    var archiveMap = mutableMapOf<String, ArrayList<Note>>()

    fun startScreen() {
        while (true) {
            printStartMenu()
            when (getDigitFromInput()) {
                0 -> createArchive()
                1 -> selectArchive()
                2 -> return
                else -> println("Такого пункта меню нет.")
            }
        }
    }

    fun printStartMenu() {
        println("-------------------------------------")
        println("0. Создать архив")
        println("1. Это мой уже созданный архив")
        println("2. Выход")
    }

    fun createArchive() {
        println("Введите название архива.")
        var archiveName = getTextFromInput()
        while (archiveMap.keys.contains(archiveName)) {
            println("Данное название уже занято, придумайте другое название архива.")
            archiveName = getTextFromInput()
        }

        archiveMap.put(archiveName, ArrayList())
        println("Архив \"${archiveName}\" успешно создан!")
    }

    fun selectArchive() {
        while (true) {
            printArchiveList()
            if (archiveMap.isEmpty()) {
                return
            }
            println("Введите номер архива.")
            val input = getDigitFromInput() - 1
            if (input in archiveMap.toList().indices) {
                openArchiveScreen(archiveMap.toList()[input].first)
                return
            } else {
                println("Такого номера архива нет")
            }
        }
    }

    fun printArchiveList() {
        println("-------------------------------------")
        if (archiveMap.isEmpty()) {
            println("Список архивов пуст. Добавьте их для начала работы")
        } else {
            println("Список архивов:")
            archiveMap.toList()
                .forEachIndexed { index, pair -> println("${index + 1}. ${pair.first}") }
        }
    }

    fun openArchiveScreen(archiveName: String) {
        while (true) {
            printNoteListMenu()
            when (getDigitFromInput()) {
                0 -> createNote(archiveName)
                1 -> selectNote(archiveName)
                2 -> return
                else -> println("Такого пункта меню нет.")
            }
        }
    }

    fun printNoteListMenu() {
        println("-------------------------------------")
        println("0. Создать заметку")
        println("1. Выбрать заметку")
        println("2. Выход")
    }

    fun createNote(archiveName: String) {
        println("Введите название заметки")
        val nameNote = getTextFromInput()
        println("Введите текст заметки")
        val messageNote = getTextFromInput()
        archiveMap.getValue(archiveName).add(Note(nameNote, messageNote))
    }

    fun getDigitFromInput(): Int {
        var inputDigit = readLine()?.toIntOrNull()
        while (inputDigit == null) {
            println("Ввод должен содержать цифру")
            inputDigit = readLine()?.toIntOrNull()

        }
        return inputDigit
    }

    fun getTextFromInput(): String {
        var inputText = readLine()
        while (inputText.isNullOrBlank()) {
            println("Ввод не должен быть пустым")
            inputText = readLine()
        }
        return inputText
    }

    fun selectNote(archiveName: String) {
        printNoteList(archiveName)
        if (archiveMap.getValue(archiveName).isEmpty())
            return

        println("Выберите номер заметки.")
        val input = getDigitFromInput() - 1

        if (input in archiveMap.toList().indices) {
            openNote(archiveMap.getValue(archiveName)[input])
        } else {
            println("Такого номера заметки нет.")
            selectNote(archiveName)
        }
    }

    fun printNoteList(archiveName: String) {
        println("-------------------------------------")
        if (archiveMap.getValue(archiveName).isEmpty()) {
            println("Список заметок пуст!")
        } else {
            println("Список заметок: ")
            archiveMap.getValue(archiveName).toList()
                .forEachIndexed { index, s -> println("${index + 1}. ${s.name}") }
        }
    }

    fun openNote(note: Note) {
        println("-------------------------------------")
        println("Заметка \"${note.name}\"\n${note.message}")
        return
    }
}