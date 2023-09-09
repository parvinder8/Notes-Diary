package com.parvinderr.notesdiary.preference

import android.content.Context
import com.parvinderr.notesdiary.utils.Constants.PreferenceConstants.Companion.APP_LAYOUT
import com.parvinderr.notesdiary.utils.Constants.PreferenceConstants.Companion.APP_THEME
import com.parvinderr.notesdiary.utils.Constants.PreferenceConstants.Companion.FILTER_TYPE
import com.parvinderr.notesdiary.utils.Constants.PreferenceConstants.Companion.FONT_FAMILY
import com.parvinderr.notesdiary.utils.Constants.PreferenceConstants.Companion.SORT_BY
import com.parvinderr.notesdiary.utils.FontFamilyEnum
import com.parvinderr.notesdiary.utils.LayoutEnum
import com.parvinderr.notesdiary.utils.NotesFilterBy
import com.parvinderr.notesdiary.utils.NotesSortBy
import com.parvinderr.notesdiary.utils.ThemeEnum
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


@Module
@InstallIn(ActivityComponent::class)
class SettingPreference @Inject constructor(@ApplicationContext private val context: Context) {

    private val sp = context.getSharedPreferences("setting_prefs", Context.MODE_PRIVATE)

    fun setThemePreference(theme: ThemeEnum = ThemeEnum.DEFAULT) {
        sp.edit().putString(APP_THEME, theme.name).apply()
    }

    fun getThemePreference(): ThemeEnum {
        if (!sp.contains(APP_THEME)) return ThemeEnum.DEFAULT
        return ThemeEnum.valueOf(
            sp.getString(APP_THEME, ThemeEnum.DEFAULT.name) ?: ThemeEnum.DEFAULT.name
        )
    }

    fun setLayoutPreference(theme: LayoutEnum = LayoutEnum.LIST) {
        sp.edit().putString(APP_LAYOUT, theme.name).apply()
    }

    fun setFilterType(type: NotesFilterBy) {
        sp.edit().putString(FILTER_TYPE, type.name).apply()
    }

    fun setSortBy(type: NotesSortBy) {
        sp.edit().putString(SORT_BY, type.name).apply()
    }

    fun getLayoutPreference(): LayoutEnum {
        if (!sp.contains(APP_LAYOUT)) return LayoutEnum.LIST
        return LayoutEnum.valueOf(
            sp.getString(APP_LAYOUT, LayoutEnum.LIST.name) ?: LayoutEnum.LIST.name
        )
    }

    fun setFontFamilyPreference(fontFamily: FontFamilyEnum) {
        sp.edit().putString(FONT_FAMILY, fontFamily.name).apply()
    }

    fun getFontFamilyPreference(): FontFamilyEnum {
        if (!sp.contains(FONT_FAMILY)) return FontFamilyEnum.SANS
        return FontFamilyEnum.valueOf(
            sp.getString(FONT_FAMILY, FontFamilyEnum.SANS.name) ?: FontFamilyEnum.SANS.name
        )
    }

    fun getFilterType(): NotesFilterBy {
        if (!sp.contains(FILTER_TYPE)) return NotesFilterBy.NONE
        return NotesFilterBy.valueOf(
            sp.getString(FILTER_TYPE, NotesFilterBy.NONE.name) ?: NotesFilterBy.NONE.name
        )
    }

    fun getSortBy(): NotesSortBy {
        if (!sp.contains(FILTER_TYPE)) return NotesSortBy.ASCENDING
        return NotesSortBy.valueOf(
            sp.getString(FILTER_TYPE, NotesSortBy.ASCENDING.name) ?: NotesSortBy.ASCENDING.name
        )
    }

}
