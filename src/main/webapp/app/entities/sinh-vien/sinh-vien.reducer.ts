import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISinhVien, defaultValue } from 'app/shared/model/sinh-vien.model';

export const ACTION_TYPES = {
  FETCH_SINHVIEN_LIST: 'sinhVien/FETCH_SINHVIEN_LIST',
  FETCH_SINHVIEN: 'sinhVien/FETCH_SINHVIEN',
  CREATE_SINHVIEN: 'sinhVien/CREATE_SINHVIEN',
  UPDATE_SINHVIEN: 'sinhVien/UPDATE_SINHVIEN',
  PARTIAL_UPDATE_SINHVIEN: 'sinhVien/PARTIAL_UPDATE_SINHVIEN',
  DELETE_SINHVIEN: 'sinhVien/DELETE_SINHVIEN',
  RESET: 'sinhVien/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISinhVien>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type SinhVienState = Readonly<typeof initialState>;

// Reducer

export default (state: SinhVienState = initialState, action): SinhVienState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SINHVIEN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SINHVIEN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_SINHVIEN):
    case REQUEST(ACTION_TYPES.UPDATE_SINHVIEN):
    case REQUEST(ACTION_TYPES.DELETE_SINHVIEN):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_SINHVIEN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_SINHVIEN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SINHVIEN):
    case FAILURE(ACTION_TYPES.CREATE_SINHVIEN):
    case FAILURE(ACTION_TYPES.UPDATE_SINHVIEN):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_SINHVIEN):
    case FAILURE(ACTION_TYPES.DELETE_SINHVIEN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SINHVIEN_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_SINHVIEN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_SINHVIEN):
    case SUCCESS(ACTION_TYPES.UPDATE_SINHVIEN):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_SINHVIEN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_SINHVIEN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/sinh-viens';

// Actions

export const getEntities: ICrudGetAllAction<ISinhVien> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_SINHVIEN_LIST,
    payload: axios.get<ISinhVien>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<ISinhVien> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SINHVIEN,
    payload: axios.get<ISinhVien>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ISinhVien> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SINHVIEN,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISinhVien> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SINHVIEN,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<ISinhVien> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_SINHVIEN,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISinhVien> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SINHVIEN,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
