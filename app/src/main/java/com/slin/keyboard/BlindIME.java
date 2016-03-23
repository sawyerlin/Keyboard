package com.slin.keyboard;

import android.app.AlertDialog;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;
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
    private int shiftKeyIndex = 12;
    private boolean isComposing = false;

    static {
        System.loadLibrary("sunpinyin");
        ArrayList<SourceType> sourceTypeArrayList = new ArrayList<>();
        sourceTypeArrayList.add(SourceType.Arabic);
        sourceTypeArrayList.add(SourceType.Latin);
        sourceTypeCycle = new Cycle<>(sourceTypeArrayList, SourceType.Arabic);
    }

    public View onCreateInputView() {
        kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.number);
        keyboard.getKeys().get(shiftKeyIndex).label = sourceType.toString();
        keyboard.getKeys().get(shiftKeyIndex + 1).label = getStringFromNative();
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
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        System.out.println(keyCode);
        switch (keyCode) {
            case Keyboard.KEYCODE_MODE_CHANGE:

                break;
            default:
                break;
        }
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
        switch(primaryCode) {
            case Keyboard.KEYCODE_DELETE:
                if (this.isComposing) {
                    if (word.length() > 0) {
                        word = word.substring(0, word.length() - 1);
                    } else {
                        word = "";
                        this.isComposing = false;
                    }
                    ic.setComposingText(word, 1);
                } else {
                    if (text.length() > 0) {
                        text = text.substring(0, text.length() - 1);
                    } else {
                        text = "";
                    }
                    ic.deleteSurroundingText(1, 0);
                }
                break;
            case Keyboard.KEYCODE_DONE:
                String currentText = Source.findValue(sourceType, word);
                text += currentText;
                word = "";
                ic.commitText(currentText, 1);
                ic.finishComposingText();
                this.isComposing = false;
                break;
            case Keyboard.KEYCODE_MODE_CHANGE:
                sourceType = sourceTypeCycle.peek();
                kv.getKeyboard().getKeys().get(shiftKeyIndex).label = sourceType.toString();
                break;
            case -3:
                //TODO: refactoring method
                if (this.isComposing) {
                    ic.commitText(word + " ", 1);
                    ic.finishComposingText();
                    word = "";
                    this.isComposing = false;
                }  else {
                    ic.commitText(" ", 1);
                }
                break;
            default:
                char code = (char)primaryCode;
                word += code;
                ic.setComposingText(word, 1);
                this.isComposing = true;
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

    public native String getStringFromNative();
}
