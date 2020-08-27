import {SAVE_BOOK_REQUEST, FETCH_BOOK_REQUEST, UPDATE_BOOK_REQUEST, DELETE_BOOK_REQUEST, BOOK_SUCCESS, BOOK_FAILURE, LIBRARY_ID} from "./bookTypes";

const initialState = {
    book: '', error: '', libraryId: ''
};

const reducer = (state = initialState, action) => {
    switch(action.type) {
        case SAVE_BOOK_REQUEST || FETCH_BOOK_REQUEST || UPDATE_BOOK_REQUEST || DELETE_BOOK_REQUEST || LIBRARY_ID:
            return {
                ...state
            };
        case BOOK_SUCCESS:
            return {
                book: action.payload,
                error: ''
            };
        case BOOK_FAILURE:
            return {
                book: '',
                error: action.payload
            };
        default: return state;
    }
};

export default reducer;