package com.androdu.infokeeper.domain.utils

import com.androdu.infokeeper.R

/**
 * Enum representing different job titles.
 */
enum class JobTitle {
    NOT_SELECTED,
    ANDROID_DEVELOPER,
    IOS_DEVELOPER,
    FRONTEND_DEVELOPER,
    BACKEND_DEVELOPER,
    JAVA_DEVELOPER,
    PYTHON_DEVELOPER,
    QA_TESTER,
    UI_UX_DESIGNER,
    PRODUCT_MANAGER,
    SCRUM_MASTER,
    OTHER;

    /**
     * Retrieves the corresponding string resource ID for each job title.
     * @return The string resource ID.
     */
    fun getStringRes(): Int = when (this) {
        NOT_SELECTED -> R.string.not_selected
        ANDROID_DEVELOPER -> R.string.android_developer
        IOS_DEVELOPER -> R.string.ios_developer
        FRONTEND_DEVELOPER -> R.string.frontend_developer
        BACKEND_DEVELOPER -> R.string.backend_developer
        JAVA_DEVELOPER -> R.string.java_developer
        PYTHON_DEVELOPER -> R.string.python_developer
        QA_TESTER -> R.string.qa_tester
        UI_UX_DESIGNER -> R.string.ui_ux_designer
        PRODUCT_MANAGER -> R.string.product_manager
        SCRUM_MASTER -> R.string.scrum_master
        OTHER -> R.string.other
    }
}

/**
 * Enum representing different genders.
 */
enum class Gender {
    NOT_SELECTED,
    MALE,
    FEMALE;

    /**
     * Retrieves the corresponding string resource ID for each gender.
     * @return The string resource ID.
     */
    fun getStringRes(): Int = when (this) {
        NOT_SELECTED -> R.string.not_selected
        MALE -> R.string.male
        FEMALE -> R.string.female
    }
}