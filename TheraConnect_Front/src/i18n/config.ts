import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';

import translation_en from './en.json';
import translation_kr from './kr.json';

const resources = {
    en: {
        translation: translation_en,
    },
    kr: {
        translation: translation_kr,
    },
};

i18n.use(initReactI18next).init({
    resources,
    lng: 'kr', // 기본 언어 설정
    interpolation: {
        escapeValue: false,
    },
});

export default i18n;
