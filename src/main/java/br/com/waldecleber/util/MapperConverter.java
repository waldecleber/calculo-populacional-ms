package br.com.waldecleber.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MapperConverter {

	public static final ModelMapper MODEL_MAPPER = new ModelMapper();
	
	private MapperConverter() {
		super();
	}
	
	public static <S, D> List<D> mapList(List<S> lista, Class<D> destinationType, PropertyMap<S, D> propertyMap) {

		ArrayList<D> listaDTO = new ArrayList<>();

		if (!lista.isEmpty()) {
			for (S source : lista) {
				listaDTO.add(map(source, destinationType, propertyMap));
			}
		}

		return listaDTO;
	}

	public static <S, D> List<D> mapList(Collection<S> lista, Class<D> destinationType) {
		if (lista == null)
			return null;
		return mapList(new ArrayList<>(lista), destinationType, null);
	}

	public static <S, D> List<D> mapList(List<S> lista, Class<D> destinationType) {
		return mapList(lista, destinationType, null);
	}

	public static <S, D> D map(S source, Class<D> destinationType, PropertyMap<S, D> propertyMap) {

		if (propertyMap != null && MODEL_MAPPER.getTypeMap(source.getClass(), destinationType) == null) {
			MODEL_MAPPER.addMappings(propertyMap);
		}

		return MODEL_MAPPER.map(source, destinationType);

	}

	public static <S, D> D map(S source, Class<D> destinationType) {
		return map(source, destinationType, null);
	}
	
}
