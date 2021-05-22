import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IMonHoc, defaultValue } from 'app/shared/model/mon-hoc.model';

export const ACTION_TYPES = {
  FETCH_MONHOC_LIST: 'monHoc/FETCH_MONHOC_LIST',
  FETCH_MONHOC: 'monHoc/FETCH_MONHOC',
  CREATE_MONHOC: 'monHoc/CREATE_MONHOC',
  UPDATE_MONHOC: 'monHoc/UPDATE_MONHOC',
  PARTIAL_UPDATE_MONHOC: 'monHoc/PARTIAL_UPDATE_MONHOC',
  DELETE_MONHOC: 'monHoc/DELETE_MONHOC',
  RESET: 'monHoc/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IMonHoc>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type MonHocState = Readonly<typeof initialState>;

// Reducer

export default (state: MonHocState = initialState, action): MonHocState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_MONHOC_LIST):
    case REQUEST(ACTION_TYPES.FETCH_MONHOC):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_MONHOC):
    case REQUEST(ACTION_TYPES.UPDATE_MONHOC):
    case REQUEST(ACTION_TYPES.DELETE_MONHOC):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_MONHOC):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_MONHOC_LIST):
    case FAILURE(ACTION_TYPES.FETCH_MONHOC):
    case FAILURE(ACTION_TYPES.CREATE_MONHOC):
    case FAILURE(ACTION_TYPES.UPDATE_MONHOC):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_MONHOC):
    case FAILURE(ACTION_TYPES.DELETE_MONHOC):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_MONHOC_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_MONHOC):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_MONHOC):
    case SUCCESS(ACTION_TYPES.UPDATE_MONHOC):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_MONHOC):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_MONHOC):
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

const apiUrl = 'api/mon-hocs';

// Actions

export const getEntities: ICrudGetAllAction<IMonHoc> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_MONHOC_LIST,
    payload: axios.get<IMonHoc>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IMonHoc> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_MONHOC,
    payload: axios.get<IMonHoc>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IMonHoc> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_MONHOC,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IMonHoc> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_MONHOC,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IMonHoc> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_MONHOC,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IMonHoc> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_MONHOC,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
