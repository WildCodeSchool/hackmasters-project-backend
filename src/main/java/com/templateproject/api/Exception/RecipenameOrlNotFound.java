package com.templateproject.api.Exception;

public class RecipenameOrlNotFound extends Exception{


        public RecipenameOrlNotFound() {
            super("Recipe or ID not found.");
        }

        public RecipenameOrlNotFound(String message) {
            super(message);
        }
}
