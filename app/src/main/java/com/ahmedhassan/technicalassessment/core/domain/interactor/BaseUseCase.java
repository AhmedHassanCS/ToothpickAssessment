package com.ahmedhassan.technicalassessment.core.domain.interactor;

import io.reactivex.Observable;
/**
 * BaseUseCase is a class for every use-case to inherit from
 * It's useful to restrict every use case to only do one specific task
 * */
public abstract class BaseUseCase<PARAM, RESULT> {

    public abstract Observable<RESULT> execute(PARAM param);
}
