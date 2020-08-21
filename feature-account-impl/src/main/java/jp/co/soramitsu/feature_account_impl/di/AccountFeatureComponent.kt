package jp.co.soramitsu.feature_account_impl.di

import dagger.BindsInstance
import dagger.Component
import jp.co.soramitsu.common.di.CommonApi
import jp.co.soramitsu.common.di.scope.FeatureScope
import jp.co.soramitsu.feature_account_api.di.AccountFeatureApi
import jp.co.soramitsu.feature_account_impl.presentation.AccountRouter
import jp.co.soramitsu.feature_account_impl.presentation.importing.di.ImportAccountComponent
import jp.co.soramitsu.feature_account_impl.presentation.mnemonic.backup.di.BackupMnemonicComponent
import jp.co.soramitsu.feature_account_impl.presentation.mnemonic.confirm.di.ConfirmMnemonicComponent
import jp.co.soramitsu.feature_account_impl.presentation.pincode.di.PinCodeComponent

@Component(
    dependencies = [
        AccountFeatureDependencies::class
    ],
    modules = [
        AccountFeatureModule::class
    ]
)
@FeatureScope
interface AccountFeatureComponent : AccountFeatureApi {

    fun importAccountComponentFactory(): ImportAccountComponent.Factory

    fun backupMnemonicComponentFactory(): BackupMnemonicComponent.Factory

    fun pincodeComponentFactory(): PinCodeComponent.Factory

    fun confirmMnemonicComponentFactory(): ConfirmMnemonicComponent.Factory

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance accountRouter: AccountRouter,
            deps: AccountFeatureDependencies
        ): AccountFeatureComponent
    }

    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface AccountFeatureDependenciesComponent : AccountFeatureDependencies
}