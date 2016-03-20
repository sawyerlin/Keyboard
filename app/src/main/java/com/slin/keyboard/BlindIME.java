package com.slin.keyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.View;
import android.view.inputmethod.InputConnection;

import com.slin.keyboard.source.Cycle;
import com.slin.keyboard.source.Source;
import com.slin.keyboard.source.SourceType;

import java.util.ArrayList;

public class BlindIME extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView kv;
    private Keyboard keyboard;
    private String word = "";
    private String text = "";
    private SourceType sourceType = SourceType.Arabic;
    private static Cycle<SourceType> sourceTypeCycle;
    static {
        ArrayList<SourceType> sourceTypeArrayList = new ArrayList<>();
        sourceTypeArrayList.add(SourceType.Arabic);
        sourceTypeArrayList.add(SourceType.Latin);
        sourceTypeCycle = new Cycle<>(sourceTypeArrayList, SourceType.Arabic);
    }

    public View onCreateInputView() {
        kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.number);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        return kv;
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
        switch(primaryCode) {
            case 0:
                if (text.length() > 0) {
                    text = text.substring(0, text.length() - 1);
                } else {
                    text = "";
                }
                ic.deleteSurroundingText(1, 0);
                break;
            case 1:
                String currentText = Source.findValue(sourceType, word);
                text += currentText;
                word = "";
                ic.commitText(currentText, 1);
                ic.finishComposingText();
                break;
            case 2:
                sourceType = sourceTypeCycle.peek();
                break;
            default:
                char code = (char)primaryCode;
                word += code;
                ic.setComposingText(word, 1);
                break;
        }
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}