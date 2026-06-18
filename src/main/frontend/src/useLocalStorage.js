import { useEffect, useState } from "react";

function useLocalState(defaultValue, key) {
    const [value, setValue] = useState(() => {
        try {
            const item = localStorage.getItem(key);
            return item !== null ? JSON.parse(item) : defaultValue;
        } catch (e) {
            return defaultValue;
        }
    });

    const setStoredValue = (newValue) => {
        setValue(newValue);
        // Set it manually and immediately
        localStorage.setItem(key, JSON.stringify(newValue));
    };

    return [value, setStoredValue];
}

export { useLocalState };