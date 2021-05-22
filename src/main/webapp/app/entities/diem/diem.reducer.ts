import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDiem, defaultValue } from 'app/shared/model/diem.model';

export const ACTION_TYPES = {
  FETCH_DIEM_LIST: 'diem/FETCH_DIEM_LIST',
  FETCH_DIEM: 'diem/FETCH_DIEM',
  CREATE_DIEM: 'diem/CREATE_DIEM',
  UPDATE_DIEM: 'diem/UPDATE_DIEM',
  PARTIAL_UPDATE_DIEM: 'diem/PARTIAL_UPDATE_DIEM',
  DELETE_DIEM: 'diem/DELETE_DIEM',
  RESET: 'diem/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDiem>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type DiemState = Readonly<typeof initialState>;

// Reducer

export default (state: DiemState = initialState, action): DiemState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DIEM_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DIEM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_DIEM):
    case REQUEST(ACTION_TYPES.UPDATE_DIEM):
    case REQUEST(ACTION_TYPES.DELETE_DIEM):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_DIEM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_DIEM_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DIEM):
    case FAILURE(ACTION_TYPES.CREATE_DIEM):
    case FAILURE(ACTION_TYPES.UPDATE_DIEM):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_DIEM):
    case FAILURE(ACTION_TYPES.DELETE_DIEM):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_DIEM_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_DIEM):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_DIEM):
    case SUCCESS(ACTION_TYPES.UPDATE_DIEM):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_DIEM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_DIEM):
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

const apiUrl = 'api/diems';

// Actions

export const getEntities: ICrudGetAllAction<IDiem> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DIEM_LIST,
    payload: axios.get<IDiem>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IDiem> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DIEM,
    payload: axios.get<IDiem>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IDiem> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DIEM,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDiem> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DIEM,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IDiem> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_DIEM,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDiem> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DIEM,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
