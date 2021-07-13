package com.hsj.assignment.di

import com.example.coordinatorlayouttest.model.DataModel
import com.example.coordinatorlayouttest.model.DataModelImpl
import com.hsj.assignment.viewmodel.DetailRepository
import com.hsj.assignment.viewmodel.DetailViewModel
import com.hsj.assignment.viewmodel.HomeRepository
import com.hsj.assignment.viewmodel.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


/**
 * single : 앱이 실행되는 동안 계속 유지되는 싱글톤 객체를 생성합니다.
 * factory : 요청할 때마다 매번 새로운 객체를 생성합니다.
 * get() : 컴포넌트 내에서 알맞은 의존성을 주입 받습니다.
 */

var repositoryPart = module{

    single{
        DetailRepository(get(),androidContext())
    }

    single{
        HomeRepository(get(),androidContext())
    }
}

var modelPart = module {
    factory<DataModel> {
        DataModelImpl()
    }
}

var viewModelPart = module {
    single {
        DetailViewModel(get())
    }

    single {
        HomeViewModel(get())
    }
}


var myDiModule = listOf(viewModelPart,repositoryPart,modelPart)
