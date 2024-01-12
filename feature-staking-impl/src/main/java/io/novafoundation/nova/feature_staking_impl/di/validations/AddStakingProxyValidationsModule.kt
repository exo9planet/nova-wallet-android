package io.novafoundation.nova.feature_staking_impl.di.validations

import dagger.Module
import dagger.Provides
import io.novafoundation.nova.common.di.scope.FeatureScope
import io.novafoundation.nova.common.validation.ValidationSystem
import io.novafoundation.nova.feature_proxy_api.data.repository.GetProxyRepository
import io.novafoundation.nova.feature_staking_impl.domain.validations.delegation.proxy.add.AddStakingProxyValidationSystem
import io.novafoundation.nova.feature_staking_impl.domain.validations.delegation.proxy.add.enoughBalanceToPayDeposit
import io.novafoundation.nova.feature_staking_impl.domain.validations.delegation.proxy.add.maximumProxies
import io.novafoundation.nova.feature_staking_impl.domain.validations.delegation.proxy.add.sufficientBalanceToPayFee
import io.novafoundation.nova.feature_staking_impl.domain.validations.delegation.proxy.add.sufficientBalanceToStayAboveEd
import io.novafoundation.nova.feature_staking_impl.domain.validations.delegation.proxy.add.validAddress
import io.novafoundation.nova.feature_wallet_api.domain.validation.EnoughTotalToStayAboveEDValidationFactory

@Module
class AddStakingProxyValidationsModule {

    @FeatureScope
    @Provides
    fun provideAddStakingProxyValidationSystem(
        getProxyRepository: GetProxyRepository,
        enoughTotalToStayAboveEDValidationFactory: EnoughTotalToStayAboveEDValidationFactory
    ): AddStakingProxyValidationSystem = ValidationSystem {
        validAddress()

        sufficientBalanceToPayFee()

        sufficientBalanceToStayAboveEd(enoughTotalToStayAboveEDValidationFactory)

        maximumProxies(getProxyRepository)

        enoughBalanceToPayDeposit(getProxyRepository)
    }
}
