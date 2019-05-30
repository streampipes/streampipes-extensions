package org.streampipes.processors.geo.jvm.processors.measureOperator.hausdorffDistanceCalc;

import org.streampipes.model.graph.DataProcessorInvocation;
import org.streampipes.wrapper.params.binding.EventProcessorBindingParams;



public class HausdorffDistanceParameter extends EventProcessorBindingParams {

    private String geom_1;
    private String epsg_geom_1;
    private String geom_2;
    private String epsg_geom_2;
    private Integer unit;
    private Integer decimalPosition;
    private Boolean vsChecker;


    public HausdorffDistanceParameter(DataProcessorInvocation graph, String geom_1, String epsg_geom_1, String geom_2, String epsg_geom_2, Integer unit, Integer decimalPosition, Boolean vsChecker) {
        super(graph);
        this.geom_1 = geom_1;
        this.epsg_geom_1 = epsg_geom_1;
        this.geom_2 = geom_2;
        this.epsg_geom_2 = epsg_geom_2;
        this.unit = unit;
        this.decimalPosition = decimalPosition;
        this.vsChecker = vsChecker;
    }


    public String getGeom_1() {
        return geom_1;
    }

    public String getEpsg_geom_1() {
        return epsg_geom_1;
    }

    public String getGeom_2() {
        return geom_2;
    }

    public String getEpsg_geom_2() {
        return epsg_geom_2;
    }

    public Integer getUnit() {
        return unit;
    }

    public Integer getDecimalPosition() {
        return decimalPosition;
    }

    public Boolean getVsChecker() {
        return vsChecker;
    }
}