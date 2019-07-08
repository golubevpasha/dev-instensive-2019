package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?,String?>{
        if (fullName != null){
            var fullNameCopy: String = fullName.toString()
            while (fullNameCopy.contains("  ")) {
                fullNameCopy = fullNameCopy.replace("  "," ")
            }
            fullNameCopy = fullNameCopy.trimStart(' ')
            val parts: List<String>? = fullNameCopy.split(" ")

            var firstName = parts?.getOrNull(0)
            var lastName =parts?.getOrNull(1)

            if (firstName != null && firstName.isBlank()) {firstName = null}
            if (lastName != null && lastName.isBlank()) {lastName = null}

            //return Pair(firstName, lastName)
            return firstName to lastName
        }
        else {
            return null to null
        }
    }

    fun transliteration(payload: String, divider : String = " "): String {
        var result = ""
        for (c:Char in payload) {
            if (c == ' ') {
                result += divider
            }
            else {
                if(literalMap.containsKey(c.toString().toLowerCase())) {
                    var strTmp = literalMap[c.toString().toLowerCase()] ?: ""
                    if (c.isUpperCase() && strTmp!="") {
                       strTmp = strTmp[0].toString().toUpperCase() + strTmp.drop(1)
                    }
                    result += strTmp
                }
                else {
                    result += c.toString()
                }
            }
        }
        return result
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        if ((firstName == null || firstName.isBlank()) && (lastName == null || lastName.isBlank())) return null
        if ((firstName == null || firstName.isBlank()) && (lastName != null && !lastName.isBlank())) return lastName[0].toString().toUpperCase()
        if ((lastName == null || lastName.isBlank()) && (firstName != null && !firstName.isBlank())) return firstName[0].toString().toUpperCase()
        if (firstName != null && !firstName.isBlank() && lastName != null && !lastName.isBlank()) return firstName[0].toString().toUpperCase() + lastName[0].toString().toUpperCase()
        return null
    }
    private val literalMap: Map<String,String> = mapOf("а" to "a", "б" to "b", "в" to "v", "г" to "g", "д" to "d", "е" to "e", "ё" to "e", "ж" to "zh", "з" to "z", "и" to "i", "й" to "i", "к" to "k", "л" to "l", "м" to "m", "н" to "n", "о" to "o", "п" to "p", "р" to "r", "с" to "s", "т" to "t", "у" to "u", "ф" to "f", "х" to "h", "ц" to "c", "ч" to "ch", "ш" to "sh", "щ" to "sh'", "ъ" to "", "ы" to "i", "ь" to "", "э" to "e", "ю" to "yu", "я" to "ya")

}