package com.melrs.mingle.ui.mingleitem.invoice;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceProcessor {

    public static void processInvoice(Context context, Uri imageUri, OnInvoiceProcessedListener listener) {
        try {
            Toast.makeText(context, "Processing... ", Toast.LENGTH_SHORT).show();
            InputImage image = InputImage.fromFilePath(context, imageUri);
            TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);

            recognizer.process(image)
                    .addOnSuccessListener(text -> {
                        List<String> mingleItems = extractMingleItems(text);
                        listener.onSuccess(mingleItems);
                    })
                    .addOnFailureListener(e -> {
                        Log.e("InvoiceProcessor", "Failed to process invoice", e);
                        listener.onFailure(e);
                    });
        } catch (IOException e) {
            Log.e("InvoiceProcessor", "Failed to process invoice", e);
            listener.onFailure(e);
        }
    }

    private static List<String> extractMingleItems(Text text) {
        List<String> mingleItems = new ArrayList<>();
        for (Text.TextBlock block : text.getTextBlocks()) {
            for (Text.Line line : block.getLines()) {
                mingleItems.add(line.getText());
            }
        }
        return mingleItems;
    }

    public interface OnInvoiceProcessedListener {
        void onSuccess(List<String> mingleItems);

        void onFailure(Exception e);
    }
}