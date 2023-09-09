package com.parvinderr.notesdiary.utils

class Constants {

    interface DateConstants {
        companion object {
            const val YEAR_MONTH_FORMATTER = "yyyy/MM/dd"
            const val HOUR_MIN_SEC_FORMATTER = "hh:mm:ss"
        }
    }

    interface PreferenceConstants {
        companion object {
            const val APP_THEME = "app_theme"
            const val APP_LAYOUT = "app_layout"
            const val FONT_FAMILY = "font_family"
            const val FILTER_TYPE = "filter_type"
            const val SORT_BY = "sort_by"
        }
    }

    interface SearchConstants {
        companion object {
            const val SEARCH_DEBOUNCING_TIME = 600L
        }
    }

}
