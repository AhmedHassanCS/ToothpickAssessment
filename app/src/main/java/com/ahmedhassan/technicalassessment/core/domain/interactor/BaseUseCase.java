package com.ahmedhassan.technicalassessment.core.domain.interactor;

import io.reactivex.Observable;

public abstract class BaseUseCase<PARAM, RESULT> {

    public abstract Observable<RESULT> execute(PARAM param);
}
