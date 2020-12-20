package ru.vladikadiroff.mediawikiprojects.utils.interfaces


interface ProjectModels {

    val VIEW_TYPE: ViewTypes

    enum class ViewTypes(val type: Int) {
        MODEL(0),
        FOOTER_EXCEPTION(1),
        FOOTER_LOADING(2)
    }

}

