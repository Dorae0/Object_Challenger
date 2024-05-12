package com.example.yolov8

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreference
import java.util.Locale

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        // 언어 설정에 대한 리스너 등록
        findPreference<ListPreference>("language_preference")?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _, newValue ->
                if (newValue is String && "language_preference" == "language_preference") {
                    // 언어 설정이 변경되었을 때 처리
                    updateLanguage(newValue)
                }
                true
            }
        findPreference<SwitchPreference>("sound_preference")?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _, newValue ->
                if (newValue is Boolean && "sound_preference" == "sound_preference") {
                    if (newValue) {
                        // 소리 켜기
                        showToast("소리가 켜졌습니다.")
                    } else {
                        // 소리 끄기
                        showToast("소리가 꺼졌습니다.")
                    }
                }
                true
            }
    }

    private fun updateLanguage(languageCode: String) {
        val resources = resources
        val configuration = resources.configuration

        // 새로운 로케일을 설정하고 업데이트
        val newLocale = Locale(languageCode)
        Locale.setDefault(newLocale)
        configuration.setLocale(newLocale)

        // 리소스 업데이트
        resources.updateConfiguration(configuration, resources.displayMetrics)

        // 언어 설정을 저장
        val sharedPreferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(requireContext())
        val editor = sharedPreferences.edit()
        editor.putString("language_preference", languageCode)
        editor.apply()

        // 액티비티를 리스타트하여 변경된 언어 적용
        requireActivity().recreate()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}